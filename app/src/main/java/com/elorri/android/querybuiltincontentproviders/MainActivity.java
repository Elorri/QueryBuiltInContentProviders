package com.elorri.android.querybuiltincontentproviders;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Since it is impossible to write into assets files because apk are packed and therefore
        // not expandable in size. I will write to external storage to be sure I can access the
        // file from my phone and send it on drop box or via email.
        File providersOverview = getFileToWriteIn();
        context = getBaseContext();
        String[] contact = gettingTheMergeContact(
                new Object[]{"ContactsContract.Contacts.CONTENT_URI", ContactsContract.Contacts.CONTENT_URI});
        String rawContacts = gettingTheMergeRawContacts(
                new Object[]{"ContactsContract.Contacts.CONTENT_URI", ContactsContract.RawContacts.CONTENT_URI}, contact[0]);
        String rawContactsData = gettingTheMergeRawContactsData(
                new Object[]{"ContactsContract.Data.CONTENT_URI", ContactsContract.Data.CONTENT_URI}, contact[0]);


        writeTableOfStringToFile(providersOverview, new String[]{contact[1], rawContacts, rawContactsData});
    }

    private String gettingTheMergeRawContactsData(Object[] uri, String contactId) {
        String desc = "Querying uri " + uri[0] + " - " + uri[1] + " results in : \n";
        Cursor cursor = null;
        try {
            String[] projection=new String[]{ContactsContract.Data._ID,
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
            cursor = context.getContentResolver().query((Uri) uri[1],
                    projection,
                    ContactsContract.Data.CONTACT_ID + " = ?",
                    new String[]{contactId},
                    ContactsContract.Data.CONTACT_ID + " asc");
            desc += describeCursor(cursor);
            return desc;
        } catch (Exception e) {
            desc += e.getMessage();
            return desc;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private String gettingTheMergeRawContacts(Object[] uri, String contactId) {
        String desc = "Querying uri " + uri[0] + " - " + uri[1] + " results in : \n";
        Cursor cursor = null;
        try {
            String[] projection=new String[]{ContactsContract.RawContacts._ID,
                    ContactsContract.RawContacts.CONTACT_ID,
                    ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY,
                    ContactsContract.RawContacts.ACCOUNT_TYPE,
                    ContactsContract.RawContacts.ACCOUNT_NAME};
            cursor = context.getContentResolver().query((Uri) uri[1],
                    projection,
                    ContactsContract.RawContacts.CONTACT_ID + " = ?",
                    new String[]{contactId},
                    ContactsContract.RawContacts.CONTACT_ID + " asc");
            desc += describeCursor(cursor);
            return desc;
        } catch (Exception e) {
            desc += e.getMessage();
            return desc;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


    private String[] gettingTheMergeContact(Object[] uri) {
        String desc = "Querying uri " + uri[0] + " - " + uri[1] + " results in : \n";
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query((Uri) uri[1],
                    new String[]{ContactsContract.Contacts._ID,
                            ContactsContract.Contacts.LOOKUP_KEY,
                            ContactsContract.Contacts.DISPLAY_NAME},
                    ContactsContract.Contacts.DISPLAY_NAME + " like ?",
                    new String[]{"1Contact%"},
                    ContactsContract.Contacts.DISPLAY_NAME + " asc");
            desc += describeCursor(cursor);
            cursor.moveToFirst();
            return new String[]{
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)), desc};
        } catch (Exception e) {
            desc += e.getMessage();
            return new String[]{desc};
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
