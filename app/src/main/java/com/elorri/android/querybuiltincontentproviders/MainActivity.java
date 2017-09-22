package com.elorri.android.querybuiltincontentproviders;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.provider.Telephony;
import android.provider.UserDictionary;
import android.provider.VoicemailContract;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Since it is impossible to write into assets files because apk are packed and therefore
        // not expandable in size. I will write to external storage to be sure I can access the
        // file from my phone and send it on drop box or via email.
        File providersOverview = getFileToWriteIn();

        //List of android builtin content providers static uris that can be queried.
        //Dynamics uris have been listed here and therefore some provider don't show up (eg
        // DocumentProvider because it uses only dynamic uris).
        Object[][] builtInUris = new Object[][]{
                {"CalendarContract.CONTENT_URI", CalendarContract.CONTENT_URI},
                {"CalendarContract.CalendarEntity.CONTENT_URI", CalendarContract.CalendarEntity.CONTENT_URI},
                {"CalendarContract.Calendars.CONTENT_URI", CalendarContract.Calendars.CONTENT_URI},
                {"CalendarContract.Attendees.CONTENT_URI", CalendarContract.Attendees.CONTENT_URI},
                {"CalendarContract.EventsEntity.CONTENT_URI", CalendarContract.EventsEntity.CONTENT_URI},
                {"CalendarContract.Events.CONTENT_URI", CalendarContract.Events.CONTENT_URI},
                {"CalendarContract.Events.CONTENT_EXCEPTION_URI", CalendarContract.Events.CONTENT_EXCEPTION_URI},
                {"CalendarContract.Instances.CONTENT_URI", CalendarContract.Instances.CONTENT_URI},
                {"CalendarContract.Instances.CONTENT_BY_DAY_URI", CalendarContract.Instances.CONTENT_BY_DAY_URI},
                {"CalendarContract.Instances.CONTENT_SEARCH_URI", CalendarContract.Instances.CONTENT_SEARCH_URI},
                {"CalendarContract.Instances.CONTENT_SEARCH_BY_DAY_URI", CalendarContract.Instances.CONTENT_SEARCH_BY_DAY_URI},
                {"CalendarContract.CalendarCache.URI", CalendarContract.CalendarCache.URI},
                {"CalendarContract.EventDays.CONTENT_URI", CalendarContract.EventDays.CONTENT_URI},
                {"CalendarContract.Reminders.CONTENT_URI", CalendarContract.Reminders.CONTENT_URI},
                {"CalendarContract.CalendarAlerts.CONTENT_URI", CalendarContract.CalendarAlerts.CONTENT_URI},
                {"CalendarContract.CalendarAlerts.CONTENT_URI_BY_INSTANCE", CalendarContract.CalendarAlerts.CONTENT_URI_BY_INSTANCE},
                {"CalendarContract.Colors.CONTENT_URI", CalendarContract.Colors.CONTENT_URI},
                {"CalendarContract.ExtendedProperties.CONTENT_URI", CalendarContract.ExtendedProperties.CONTENT_URI},
                {"CalendarContract.SyncState.CONTENT_URI", CalendarContract.SyncState.CONTENT_URI},
                {"CallLog.CONTENT_URI", CallLog.CONTENT_URI},
                {"CallLog.Calls.CONTENT_URI", CallLog.Calls.CONTENT_URI},
                {"CallLog.Calls.CONTENT_FILTER_URI", CallLog.Calls.CONTENT_FILTER_URI},
                {"CallLog.Calls.CONTENT_URI_WITH_VOICEMAIL", CallLog.Calls.CONTENT_URI_WITH_VOICEMAIL},
                {"ContactsContract.AUTHORITY_URI", ContactsContract.AUTHORITY_URI},
                {"ContactsContract.Directory.CONTENT_URI", ContactsContract.Directory.CONTENT_URI},
                {"ContactsContract.SyncState.CONTENT_URI", ContactsContract.SyncState.CONTENT_URI},
                {"ContactsContract.ProfileSyncState.CONTENT_URI", ContactsContract.ProfileSyncState.CONTENT_URI},
                {"ContactsContract.Contacts.CONTENT_URI", ContactsContract.Contacts.CONTENT_URI},
                {"ContactsContract.Contacts.CONTENT_LOOKUP_URI", ContactsContract.Contacts.CONTENT_LOOKUP_URI},
                {"ContactsContract.Contacts.CONTENT_VCARD_URI", ContactsContract.Contacts.CONTENT_VCARD_URI},
                {"ContactsContract.Contacts.CONTENT_MULTI_VCARD_URI", ContactsContract.Contacts.CONTENT_MULTI_VCARD_URI},
                {"ContactsContract.Contacts.CONTENT_FILTER_URI", ContactsContract.Contacts.CONTENT_FILTER_URI},
                {"ContactsContract.Contacts.CONTENT_STREQUENT_URI", ContactsContract.Contacts.CONTENT_STREQUENT_URI},
                {"ContactsContract.Contacts.CONTENT_FREQUENT_URI", ContactsContract.Contacts.CONTENT_FREQUENT_URI},
                {"ContactsContract.Contacts.CONTENT_STREQUENT_FILTER_URI", ContactsContract.Contacts.CONTENT_STREQUENT_FILTER_URI},
                {"ContactsContract.Contacts.CONTENT_GROUP_URI", ContactsContract.Contacts.CONTENT_GROUP_URI},
                {"ContactsContract.Profile.CONTENT_URI", ContactsContract.Profile.CONTENT_URI},
                {"ContactsContract.Profile.CONTENT_VCARD_URI", ContactsContract.Profile.CONTENT_VCARD_URI},
                {"ContactsContract.Profile.CONTENT_RAW_CONTACTS_URI", ContactsContract.Profile.CONTENT_RAW_CONTACTS_URI},
                {"ContactsContract.DeletedContacts.CONTENT_URI", ContactsContract.DeletedContacts.CONTENT_URI},
                {"ContactsContract.RawContacts.CONTENT_URI", ContactsContract.RawContacts.CONTENT_URI},
                {"ContactsContract.Data.CONTENT_URI", ContactsContract.Data.CONTENT_URI},
                {"ContactsContract.RawContactsEntity.CONTENT_URI", ContactsContract.RawContactsEntity.CONTENT_URI},
                {"ContactsContract.RawContactsEntity.PROFILE_CONTENT_URI", ContactsContract.RawContactsEntity.PROFILE_CONTENT_URI},
                {"ContactsContract.PhoneLookup.CONTENT_FILTER_URI", ContactsContract.PhoneLookup.CONTENT_FILTER_URI},
                {"ContactsContract.StatusUpdates.CONTENT_URI", ContactsContract.StatusUpdates.CONTENT_URI},
                {"ContactsContract.StatusUpdates.PROFILE_CONTENT_URI", ContactsContract.StatusUpdates.PROFILE_CONTENT_URI},
                {"ContactsContract.CommonDataKinds.Phone.CONTENT_URI", ContactsContract.CommonDataKinds.Phone.CONTENT_URI},
                {"ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI", ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI},
                {"ContactsContract.CommonDataKinds.Email.CONTENT_URI", ContactsContract.CommonDataKinds.Email.CONTENT_URI},
                {"ContactsContract.CommonDataKinds.Email.CONTENT_LOOKUP_URI", ContactsContract.CommonDataKinds.Email.CONTENT_LOOKUP_URI},
                {"ContactsContract.CommonDataKinds.Email.CONTENT_FILTER_URI", ContactsContract.CommonDataKinds.Email.CONTENT_FILTER_URI},
                {"ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI", ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI},
                {"ContactsContract.CommonDataKinds.Callable.CONTENT_URI", ContactsContract.CommonDataKinds.Callable.CONTENT_URI},
                {"ContactsContract.CommonDataKinds.Callable.CONTENT_FILTER_URI", ContactsContract.CommonDataKinds.Callable.CONTENT_FILTER_URI},
                {"ContactsContract.CommonDataKinds.Contactables.CONTENT_URI", ContactsContract.CommonDataKinds.Contactables.CONTENT_URI},
                {"ContactsContract.CommonDataKinds.Contactables.CONTENT_FILTER_URI", ContactsContract.CommonDataKinds.Contactables.CONTENT_FILTER_URI},
                {"ContactsContract.Groups.CONTENT_URI", ContactsContract.Groups.CONTENT_URI},
                {"ContactsContract.Groups.CONTENT_SUMMARY_URI", ContactsContract.Groups.CONTENT_SUMMARY_URI},
                {"ContactsContract.AggregationExceptions.CONTENT_URI", ContactsContract.AggregationExceptions.CONTENT_URI},
                {"ContactsContract.Settings.CONTENT_URI", ContactsContract.Settings.CONTENT_URI},
                {"ContactsContract.DataUsageFeedback.FEEDBACK_URI", ContactsContract.DataUsageFeedback.FEEDBACK_URI},
                {"ContactsContract.DataUsageFeedback.DELETE_USAGE_URI", ContactsContract.DataUsageFeedback.DELETE_USAGE_URI},
                {"ContactsContract.DisplayPhoto.CONTENT_URI", ContactsContract.DisplayPhoto.CONTENT_URI},
                {"ContactsContract.DisplayPhoto.CONTENT_MAX_DIMENSIONS_URI", ContactsContract.DisplayPhoto.CONTENT_MAX_DIMENSIONS_URI},
                {"MediaStore.Images.Media.EXTERNAL_CONTENT_URI", MediaStore.Images.Media.EXTERNAL_CONTENT_URI},
                {"MediaStore.Images.Media.INTERNAL_CONTENT_URI", MediaStore.Images.Media.INTERNAL_CONTENT_URI},
                {"MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI", MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI},
                {"MediaStore.Images.Thumbnails.INTERNAL_CONTENT_URI", MediaStore.Images.Thumbnails.INTERNAL_CONTENT_URI},
                {"MediaStore.Audio.Media.EXTERNAL_CONTENT_URI", MediaStore.Audio.Media.EXTERNAL_CONTENT_URI},
                {"MediaStore.Audio.Media.INTERNAL_CONTENT_URI", MediaStore.Audio.Media.INTERNAL_CONTENT_URI},
                {"MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI", MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI},
                {"MediaStore.Audio.Genres.INTERNAL_CONTENT_URI", MediaStore.Audio.Genres.INTERNAL_CONTENT_URI},
                {"MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI", MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI},
                {"MediaStore.Audio.Playlists.INTERNAL_CONTENT_URI", MediaStore.Audio.Playlists.INTERNAL_CONTENT_URI},
                {"MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI", MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI},
                {"MediaStore.Audio.Artists.INTERNAL_CONTENT_URI", MediaStore.Audio.Artists.INTERNAL_CONTENT_URI},
                {"MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI", MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI},
                {"MediaStore.Audio.Albums.INTERNAL_CONTENT_URI", MediaStore.Audio.Albums.INTERNAL_CONTENT_URI},
                {"MediaStore.Video.Media.EXTERNAL_CONTENT_URI", MediaStore.Video.Media.EXTERNAL_CONTENT_URI},
                {"MediaStore.Video.Media.INTERNAL_CONTENT_URI", MediaStore.Video.Media.INTERNAL_CONTENT_URI},
                {"MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI", MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI},
                {"MediaStore.Video.Thumbnails.INTERNAL_CONTENT_URI", MediaStore.Video.Thumbnails.INTERNAL_CONTENT_URI},
                {"Settings.System.CONTENT_URI", Settings.System.CONTENT_URI},
                {"Settings.System.DEFAULT_ALARM_ALERT_URI", Settings.System.DEFAULT_ALARM_ALERT_URI},
                {"Settings.System.DEFAULT_NOTIFICATION_URI", Settings.System.DEFAULT_NOTIFICATION_URI},
                {"Settings.System.DEFAULT_RINGTONE_URI", Settings.System.DEFAULT_RINGTONE_URI},
                {"Settings.Secure.CONTENT_URI", Settings.Secure.CONTENT_URI},
                {"Settings.Global.CONTENT_URI", Settings.Global.CONTENT_URI},
                {"Telephony.Sms.CONTENT_URI", Telephony.Sms.CONTENT_URI},
                {"Telephony.Sms.Inbox.CONTENT_URI", Telephony.Sms.Inbox.CONTENT_URI},
                {"Telephony.Sms.Sent.CONTENT_URI", Telephony.Sms.Sent.CONTENT_URI},
                {"Telephony.Sms.Draft.CONTENT_URI", Telephony.Sms.Draft.CONTENT_URI},
                {"Telephony.Sms.Outbox.CONTENT_URI", Telephony.Sms.Outbox.CONTENT_URI},
                {"Telephony.Sms.Conversations.CONTENT_URI", Telephony.Sms.Conversations.CONTENT_URI},
                {"Telephony.Threads.CONTENT_URI", Telephony.Threads.CONTENT_URI},
                {"Telephony.Threads.OBSOLETE_THREADS_URI", Telephony.Threads.OBSOLETE_THREADS_URI},
                {"Telephony.Mms.CONTENT_URI", Telephony.Mms.CONTENT_URI},
                {"Telephony.Mms.REPORT_REQUEST_URI", Telephony.Mms.REPORT_REQUEST_URI},
                {"Telephony.Mms.REPORT_STATUS_URI", Telephony.Mms.REPORT_STATUS_URI},
                {"Telephony.Mms.Inbox.CONTENT_URI", Telephony.Mms.Inbox.CONTENT_URI},
                {"Telephony.Mms.Sent.CONTENT_URI", Telephony.Mms.Sent.CONTENT_URI},
                {"Telephony.Mms.Draft.CONTENT_URI", Telephony.Mms.Draft.CONTENT_URI},
                {"Telephony.Mms.Outbox.CONTENT_URI", Telephony.Mms.Outbox.CONTENT_URI},
                {"Telephony.Mms.Rate.CONTENT_URI", Telephony.Mms.Rate.CONTENT_URI},
                {"Telephony.MmsSms.CONTENT_URI", Telephony.MmsSms.CONTENT_URI},
                {"Telephony.MmsSms.CONTENT_CONVERSATIONS_URI", Telephony.MmsSms.CONTENT_CONVERSATIONS_URI},
                {"Telephony.MmsSms.CONTENT_FILTER_BYPHONE_URI", Telephony.MmsSms.CONTENT_FILTER_BYPHONE_URI},
                {"Telephony.MmsSms.CONTENT_UNDELIVERED_URI", Telephony.MmsSms.CONTENT_UNDELIVERED_URI},
                {"Telephony.MmsSms.CONTENT_DRAFT_URI", Telephony.MmsSms.CONTENT_DRAFT_URI},
                {"Telephony.MmsSms.CONTENT_LOCKED_URI", Telephony.MmsSms.CONTENT_LOCKED_URI},
                {"Telephony.MmsSms.SEARCH_URI", Telephony.MmsSms.SEARCH_URI},
                {"Telephony.MmsSms.PendingMessages.CONTENT_URI", Telephony.MmsSms.PendingMessages.CONTENT_URI},
                {"Telephony.Carriers.CONTENT_URI", Telephony.Carriers.CONTENT_URI},
                {"UserDictionary.CONTENT_URI", UserDictionary.CONTENT_URI},
                {"UserDictionary.Words.CONTENT_URI", UserDictionary.Words.CONTENT_URI},
                {"VoicemailContract.Voicemails.CONTENT_URI", VoicemailContract.Voicemails.CONTENT_URI},
                {"VoicemailContract.Status.CONTENT_URI", VoicemailContract.Status.CONTENT_URI}
        };


        String[] builtInProviderDesc = describeUris(this, builtInUris);
        writeTableOfStringToFile(providersOverview, builtInProviderDesc);
    }

    private String[] describeUris(Context context, Object[][] uris) {
        String[] urisDescs = new String[uris.length];

        int i = 0;
        for (Object[] anUri : uris) {
            urisDescs[i] = describeUri(context, anUri);
            i++;
        }
        return urisDescs;
    }

    private String describeUri(Context context, Object[] uri) {
        String desc = "Querying uri " + uri[0] + " - " + uri[1] + " results in : \n";
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query((Uri)uri[1], null, null, null, null);
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

    private String describeCursor(Cursor cursor) {
        //On the first line print the columns names
        String desc = describeColumns(cursor);
        desc += describeRows(cursor);
        return desc;
    }

    private String describeRows(Cursor cursor) {
        String desc = "";
        int i = 0;
        while (cursor.moveToNext()) {
            desc += describeRow(cursor);
            if (i == 10) {//Right now we only want a sample. Not all the table that can be
                // really big.
                break;
            }
            i++;
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
