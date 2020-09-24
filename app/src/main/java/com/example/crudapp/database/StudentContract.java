package com.example.crudapp.database;

import android.provider.BaseColumns;

public class StudentContract  {
    private StudentContract(){};
    public static class FeedEntry implements BaseColumns{
        public static final String _ID = "id";
        public static final String TABLE_NAME = "students";
        public static final String COLUMN_NAME = "firstname";
        public static final String COLUMN_EMAIL = "email";
    }
}
