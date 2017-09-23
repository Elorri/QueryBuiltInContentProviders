package com.elorri.android.querybuiltincontentproviders;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    private DbHelper openHelper;
    private SQLiteDatabase db;
    private boolean writePermission;

    private String mRawContactId;
    private Cursor mCursorWithRawContactIdInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        openHelper = new DbHelper(mContext);
        db = openHelper.getWritableDatabase();

        //Since it is impossible to write into assets files because apk are packed and therefore
        // not expandable in size. I will write to external storage to be sure I can access the
        // file from my phone and send it on drop box or via email.
        File providersOverview = getFileToWriteIn();
        mContext = getBaseContext();

        preparePushContactsTo("com.google", "etchemendy.elorri@gmail.com");

        //writeTableOfStringToFile(providersOverview, new String[]{contactId, rawContacts[1], rawContactsData});
    }

    /**
     * This method will browse all contacts in the contact provider and read all it's related raw
     * contacts and merge all raw contact data into one signe raw contact that can be
     * synchronized with serveur using  accountType and accountName given.
     *
     * @param accountType for sync.
     * @param accountName for sync.
     */
    private void preparePushContactsTo(String accountType, String accountName) {
        String contactId = gettingTheMergeContactId("1 Contact%");
        preparePushContactTo(contactId, accountType, accountName);
    }

    private void preparePushContactTo(String contactId, String accountType, String accountName) {
        //Select the first raw contact with account name given for synchro
        Cursor rawContactsGivenForSync = selectFirstRawContactGivenForAccountSync(contactId, accountType, accountName);
        rawContactsGivenForSync.moveToFirst();
        String rawContactIdGivenForSync = rawContactsGivenForSync.getString(rawContactsGivenForSync
                .getColumnIndex(ContactsContract.RawContacts._ID));

        //Only for test purposes
        Cursor rawContactsDataFull = selectSyncRawContactsMimetypeDataFull(contactId);
        createTableAs("rawContactsDataFullTable", rawContactsDataFull);

        //Select all stored data for this given contact
        Cursor rawContactsData = selectSyncRawContactsMimetypeDatas(contactId);
        String rawContactsDataTable = "rawContactsDataTable";
        createTableAs(rawContactsDataTable, rawContactsData);

        //Select all stored data for raw contact associated with the account type
        Cursor rawContactData = selectSyncRawContactMimetypeDatas(rawContactIdGivenForSync);
        String rawContactDataTable = "rawContactDataTable";
        createTableAs(rawContactDataTable, rawContactData);

        //Because we want to select a distinct of each table
        rawContactsData = distinctTable(rawContactsDataTable);
        rawContactData = distinctTable(rawContactDataTable);
        createTableAs(rawContactsDataTable, rawContactsData);
        createTableAs(rawContactDataTable, rawContactData);

        //Remove the data stored in the raw contact associated with the account type
        // (rawContactsDataString - rawContactDataString)
        Cursor cursorToCompleteSynRawContact = minus(rawContactsDataTable, rawContactDataTable);
        createTableAs("minus", cursorToCompleteSynRawContact);
        cursorToCompleteSynRawContact.moveToPosition(-1);

        //Add infos to rawContactGivenForSync
        completeRawContactGivenForSync(rawContactIdGivenForSync, cursorToCompleteSynRawContact);

        //Remove other remaining rawContactForSync
        removeOtherRawContactGivenForSync(rawContactsGivenForSync);

        //Only for checking delete has worked properly during tests
        //Only for test purposes
        rawContactsDataFull = selectSyncRawContactsMimetypeDataFull(contactId);
        createTableAs("rawContactsDataFullTableAfter", rawContactsDataFull);

        //Select all stored data for this given contact
        rawContactsData = selectSyncRawContactsMimetypeDatas(contactId);
        createTableAs("rawContactsDataTableAfter", rawContactsData);

        //Close cursors and delete now useless tables


    }

    private void removeOtherRawContactGivenForSync(Cursor rawContactsGivenForSync) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();

        while (rawContactsGivenForSync.moveToNext()) {
            String rawContactIdGivenForSync = rawContactsGivenForSync.getString
                    (rawContactsGivenForSync
                    .getColumnIndex(ContactsContract.RawContacts._ID));
            ops.add(ContentProviderOperation.newDelete(
                    ContactsContract.RawContacts.CONTENT_URI)
                    .withSelection(ContactsContract.RawContacts._ID + " = ?", new String[]{rawContactIdGivenForSync})
                    .build());
        }

        try {
            mContext.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void completeRawContactGivenForSync(String rawContactId, Cursor cursorWithRawContactInfos) {

//        if (!writePermission) {
//            mRawContactId = rawContactId;
//            mCursorWithRawContactIdInfos = cursorWithRawContactInfos;
//            PlatformUtils.checkAndAskForPermission(this, Manifest.permission.WRITE_CONTACTS);
//            //After this point you wait for callback in onRequestPermissionsResult
//            return;
//        }

        ArrayList<ContentProviderOperation> ops = new ArrayList<>();

        while (cursorWithRawContactInfos.moveToNext()) {
            opsAddRawContactData(ops, rawContactId, cursorWithRawContactInfos);
        }
        try {
            mContext.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void opsAddRawContactData(ArrayList<ContentProviderOperation> ops, String rawContactId, Cursor cursorWithawContactInfos) {
        ops.add(ContentProviderOperation.newInsert(
                ContactsContract.Data.CONTENT_URI)
                .withValue(ContactsContract.Data.RAW_CONTACT_ID, Integer.parseInt(rawContactId))
                .withValue(ContactsContract.Data.MIMETYPE,
                        cursorWithawContactInfos.getString(cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.MIMETYPE)))
                .withValue(ContactsContract.Data.DATA1,
                        filterNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA1))))
                .withValue(ContactsContract.Data.DATA2,
                        filterNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA2))))
                .withValue(ContactsContract.Data.DATA3,
                        filterNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA3))))
                .withValue(ContactsContract.Data.DATA4,
                        filterNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA4))))
                .withValue(ContactsContract.Data.DATA5,
                        filterNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA5))))
                .withValue(ContactsContract.Data.DATA6,
                        filterNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA6))))
                .withValue(ContactsContract.Data.DATA7,
                        filterNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA7))))
                .withValue(ContactsContract.Data.DATA8,
                        filterNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA8))))
                .withValue(ContactsContract.Data.DATA9,
                        filterNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA9))))
                .withValue(ContactsContract.Data.DATA11,
                        filterNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA11))))
                .withValue(ContactsContract.Data.DATA10,
                        filterNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA10))))
                .withValue(ContactsContract.Data.DATA12,
                        filterNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA12))))
                .withValue(ContactsContract.Data.DATA13,
                        filterNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA13))))
                .withValue(ContactsContract.Data.DATA14,
                        filterNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA14))))
                .withValue(ContactsContract.Data.DATA15,
                        filterNull(cursorWithawContactInfos.getString(
                                cursorWithawContactInfos.getColumnIndex(ContactsContract.Data.DATA15))))
                .build());
    }

    private Object filterNull(String string) {
        if (string.equals("null")) {
            return null;
        }
        return string;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PlatformUtils.PERMISSIONS_REQUEST_WRITE_CONTACTS) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            writePermission = true;
//            completeRawContactGivenForSync(mRawContactId, mCursorWithRawContactIdInfos);
//            } else {
//                Toast.makeText(this, "Until you grant the permission, we cannot proceed", Toast.LENGTH_SHORT).show();
//            }
        }
    }

    private Cursor distinctTable(String name) {
        return db.query(true, name, null, null, null, null, null, null, null);
    }

    private void createTableAs(String name, Cursor cursor) {
        String cursorCreateString = createTableAsString(name, cursor);
        if (cursorCreateString == null) {
            return;
        }
        db.execSQL("DROP TABLE IF EXISTS " + name);
        db.execSQL(cursorCreateString);
    }

    private Cursor minus(String tableA, String tableB) {
        //In short
        //SELECT A_label as label FROM (select A.label as A_label, B.label as B_label from rawContactsDataTable A left outer join rawContactDataTable B on A.label=B.label) WHERE B_label is null)
        String table = "(select " +
                "A.mimetype as A_mimetype, " +
                "A.data1 as A_data1, " +
                "A.data2 as A_data2, " +
                "A.data3 as A_data3, " +
                "A.data4 as A_data4, " +
                "A.data5 as A_data5, " +
                "A.data6 as A_data6, " +
                "A.data7 as A_data7, " +
                "A.data8 as A_data8, " +
                "A.data9 as A_data9, " +
                "A.data10 as A_data10, " +
                "A.data11 as A_data11, " +
                "A.data12 as A_data12, " +
                "A.data13 as A_data13, " +
                "A.data14 as A_data14, " +
                "A.data15 as A_data15, " +
                "B.mimetype as B_mimetype " +
                "from " + tableA + " A left outer join " + tableB + " B " +
                "on A.mimetype=B.mimetype " +
                "and A.data1=B.data1 " +
                "and A.data2=B.data2 " +
                "and A.data3=B.data3 " +
                "and A.data4=B.data4 " +
                "and A.data5=B.data5 " +
                "and A.data6=B.data6 " +
                "and A.data7=B.data7 " +
                "and A.data8=B.data8 " +
                "and A.data9=B.data9 " +
                "and A.data10=B.data10 " +
                "and A.data11=B.data11 " +
                "and A.data12=B.data12 " +
                "and A.data13=B.data13 " +
                "and A.data14=B.data14 " +
                "and A.data15=B.data15" +
                ")";
        String[] projection = new String[]{"A_mimetype as mimetype, " +
                "A_data1 as data1, " +
                "A_data2 as data2, " +
                "A_data3 as data3, " +
                "A_data4 as data4, " +
                "A_data5 as data5, " +
                "A_data6 as data6, " +
                "A_data7 as data7, " +
                "A_data8 as data8, " +
                "A_data9 as data9, " +
                "A_data10 as data10, " +
                "A_data11 as data11, " +
                "A_data12 as data12, " +
                "A_data13 as data13, " +
                "A_data14 as data14, " +
                "A_data15 as data15"};
        String selection = "B_mimetype is null";
        return db.query(table, projection, selection, null, null, null, null);
    }


    private String createTableAsString(String name, Cursor cursor) {
        int columnCount = cursor.getColumnCount();
        int rowCount = cursor.getCount();
        if (rowCount < 1) {
            return null;
        }
        String create = "create table " + name + " as ";
        cursor.moveToPosition(-1);
        int i = 0;
        while (cursor.moveToNext()) {
            create += "select ";
            int j = 0;
            for (String column : cursor.getColumnNames()) {
                create += "'" + cursor.getString(j) + "' as " + column;
                if (j < columnCount - 1) {
                    create += ", ";
                }
                j++;
            }
            if (i < rowCount - 1) {
                create += " union ";
            }
            i++;
        }
        create += "";

        return create;
    }

    private Cursor selectSyncRawContactsMimetypeDataFull(String contactId) {
        String[] projection = new String[]{ContactsContract.Data._ID,
                ContactsContract.Data.CONTACT_ID,
                ContactsContract.Data.RAW_CONTACT_ID,
                ContactsContract.Data.LOOKUP_KEY,
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.RawContacts.ACCOUNT_TYPE,
                ContactsContract.RawContacts.ACCOUNT_NAME,
                ContactsContract.Data.MIMETYPE,
                ContactsContract.Data.DATA1,
                ContactsContract.Data.DATA2,
                ContactsContract.Data.DATA3,
                ContactsContract.Data.DATA4,
                ContactsContract.Data.DATA5,
                ContactsContract.Data.DATA6,
                ContactsContract.Data.DATA7,
                ContactsContract.Data.DATA8,
                ContactsContract.Data.DATA9,
                ContactsContract.Data.DATA10,
                ContactsContract.Data.DATA11,
                ContactsContract.Data.DATA12,
                ContactsContract.Data.DATA13,
                ContactsContract.Data.DATA14,
                ContactsContract.Data.DATA15};
        return mContext.getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                projection,
                ContactsContract.Data.CONTACT_ID + " = ?",
                new String[]{contactId},
                ContactsContract.Data.CONTACT_ID + " asc");
    }

    private Cursor selectSyncRawContactMimetypeDatas(String rawContactId) {
        String[] projection = new String[]{
                ContactsContract.Data.MIMETYPE,
                ContactsContract.Data.DATA1,
                ContactsContract.Data.DATA2,
                ContactsContract.Data.DATA3,
                ContactsContract.Data.DATA4,
                ContactsContract.Data.DATA5,
                ContactsContract.Data.DATA6,
                ContactsContract.Data.DATA7,
                ContactsContract.Data.DATA8,
                ContactsContract.Data.DATA9,
                ContactsContract.Data.DATA10,
                ContactsContract.Data.DATA11,
                ContactsContract.Data.DATA12,
                ContactsContract.Data.DATA13,
                ContactsContract.Data.DATA14,
                ContactsContract.Data.DATA15};
        return mContext.getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                projection,
                ContactsContract.Data.RAW_CONTACT_ID + " = ?",
                new String[]{rawContactId},
                null);
    }

    private String[][] convertToTable(Cursor cursor) {
        int columnCount = cursor.getColumnCount();
        int rowCount = cursor.getCount();

        String[][] table = new String[rowCount][columnCount];
        int i = 0;
        while (cursor.moveToNext()) {
            for (int j = 0; j < columnCount; j++) {
                table[i][j] = cursor.getString(j);
            }
            i++;
        }

        return table;
    }

    private Cursor selectSyncRawContactsMimetypeDatas(String contactId) {
        String[] projection = new String[]{
                ContactsContract.Data.MIMETYPE,
                ContactsContract.Data.DATA1,
                ContactsContract.Data.DATA2,
                ContactsContract.Data.DATA3,
                ContactsContract.Data.DATA4,
                ContactsContract.Data.DATA5,
                ContactsContract.Data.DATA6,
                ContactsContract.Data.DATA7,
                ContactsContract.Data.DATA8,
                ContactsContract.Data.DATA9,
                ContactsContract.Data.DATA10,
                ContactsContract.Data.DATA11,
                ContactsContract.Data.DATA12,
                ContactsContract.Data.DATA13,
                ContactsContract.Data.DATA14,
                ContactsContract.Data.DATA15};
        return mContext.getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                projection,
                ContactsContract.Data.CONTACT_ID + " = ?",
                new String[]{contactId},
                null);
    }

    private Cursor selectFirstRawContactGivenForAccountSync(String contactId,
                                                            String accountType, String accountName) {
        String[] projection = new String[]{ContactsContract.RawContacts._ID,
                ContactsContract.RawContacts.CONTACT_ID,
                ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY,
                ContactsContract.RawContacts.ACCOUNT_TYPE,
                ContactsContract.RawContacts.ACCOUNT_NAME};
        return mContext.getContentResolver().query(
                ContactsContract.RawContacts.CONTENT_URI,
                projection,
                ContactsContract.RawContacts.CONTACT_ID + " = ? and " +
                        ContactsContract.RawContacts.ACCOUNT_TYPE + " = ? and " +
                        ContactsContract.RawContacts.ACCOUNT_NAME + " = ? ",
                new String[]{contactId, accountType, accountName},
                ContactsContract.RawContacts.CONTACT_ID + " asc");
    }


    private String gettingTheMergeContactId(String contactName) {
        Cursor cursor = null;
        try {
            cursor = mContext.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                    new String[]{ContactsContract.Contacts._ID,
                            ContactsContract.Contacts.LOOKUP_KEY,
                            ContactsContract.Contacts.DISPLAY_NAME},
                    ContactsContract.Contacts.DISPLAY_NAME + " like ?",
                    new String[]{contactName},
                    ContactsContract.Contacts.DISPLAY_NAME + " asc");
            cursor.moveToFirst();
            return cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
        } catch (Exception e) {
            return null;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private String describeCursor(Cursor cursor) {
        //On the first line print the columns names
        String desc = describeColumns(cursor);
        desc += describeRows(cursor);
        return desc;
    }

    private String describeRows(Cursor cursor) {
        String desc = "";
        while (cursor.moveToNext()) {
            desc += describeRow(cursor);
            //We should make sure our cursor is filtered and does not have too musch rows.
        }
        return desc;
    }

    private String describeRow(Cursor cursor) {
        String desc = "";
        String[] columnsNames = cursor.getColumnNames();
        int columnCount = columnsNames.length;
        int i = 0;
        for (String columnName : columnsNames) {
            desc += cursor.getString(cursor.getColumnIndex(columnName));
            if (i == columnCount - 1) {
                desc += "\n";
            } else {
                desc += "|";
            }
            i++;
        }
        return desc;
    }

    private String describeColumns(Cursor cursor) {
        String desc = "";
        String[] columnsNames = cursor.getColumnNames();
        int columnCount = columnsNames.length;
        int i = 0;
        for (String columnName : columnsNames) {
            desc += columnName;
            if (i == columnCount - 1) {
                desc += "\n";
            } else {
                desc += "|";
            }
            i++;
        }
        return desc;
    }

    private void writeTableOfStringToFile(File providersOverview, String[] strings) {
        try (FileWriter fw = new FileWriter(providersOverview, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (String aString : strings) {
                out.println(aString);
                out.println("\n");
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public File getFileToWriteIn() {
        File providersOverviewDir = FileUtils.getPublicAppDir(this, "AppDir");
        FileUtils.deleteFiles(providersOverviewDir);
        return FileUtils.createFile(providersOverviewDir, new File
                (providersOverviewDir, "providersOverview.txt"));
    }
}
