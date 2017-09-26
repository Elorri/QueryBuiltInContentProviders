package com.elorri.android.querybuiltincontentproviders;

import android.provider.ContactsContract;

/**
 * Created by Elorri on 26/09/2017.
 */
public class TieUsContract {

    public static final String CURSOR_ITEM_BASE_TYPE = "vnd.com.android.tieus.item";
    public static final String CURSOR_DIR_BASE_TYPE = "vnd.com.android.tieus.dir";

    public static final class Contact {
        public static String PATH_CONTACT = "contact";

        /**
         * This utility class cannot be instantiated
         */
        private Contact() {
        }

        /**
         * MIME type used when storing this in data table.
         */
        public static final String CONTENT_ITEM_TYPE = CURSOR_ITEM_BASE_TYPE + "/" + PATH_CONTACT;
        public static final String CONTENT_TYPE = CURSOR_DIR_BASE_TYPE + "/" + PATH_CONTACT;

        public static final String CONTACT_ID = ContactsContract.Data.DATA1;
        public static final String LOOKUP_KEY = ContactsContract.Data.DATA2;
        public static final String SATISFACTION = ContactsContract.Data.DATA3;
        public static final String RESPONSE_EXPECTED_TIME_LIMIT = ContactsContract.Data.DATA4;
        public static final String RESPONSE_INCREASED_EXPECTED_TIME_LIMIT = ContactsContract.Data.DATA5;
        public static final String FREQUENCY_OF_CONTACT = ContactsContract.Data.DATA6;
        public static final String LAST_SATISFACTION_DECREASED = ContactsContract.Data.DATA7;
        public static final String UNFOLLOWED = ContactsContract.Data.DATA8;
        public static final String SATISFACTION_UNKNOWN = ContactsContract.Data.DATA9;
        public static final String BACKGROUND_COLOR = ContactsContract.Data.DATA10;
    }
}
