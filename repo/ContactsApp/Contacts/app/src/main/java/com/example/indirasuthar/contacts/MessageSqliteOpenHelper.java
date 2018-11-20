package com.example.indirasuthar.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class MessageSqliteOpenHelper extends SQLiteOpenHelper {

    // db version and name

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CONTACTS_DB";

    // table and column name
    private static final String TABLE_MESSAGES = "MESSAGES";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_FIRSTNAME = "FIRSTNAME";
    private static final String COLUMN_LASTNAME = "LASTNAME";
    private static final String COLUMN_MOBILENUMBER = "MOBILENUMBER";
    private static final String COLUMN_OTP = "OTP";
    private static final String COLUMN_TIME = "TIME";


    private static final String[] COLUMNS = {COLUMN_ID,COLUMN_FIRSTNAME,COLUMN_LASTNAME,COLUMN_MOBILENUMBER,COLUMN_OTP,COLUMN_TIME};



    public MessageSqliteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_BOOK_TABLE = "CREATE TABLE MESSAGES ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstname TEXT, "+
                "lastname TEXT ," +
                "mobilenumber TEXT,"+
                "otp TEXT ,"+
                "time TEXT)";

        // create books table
        sqLiteDatabase.execSQL(CREATE_BOOK_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // Drop older books table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS BOOK");

        // create fresh books table
        this.onCreate(sqLiteDatabase);


    }

    public void addMessage(Messages message){

        // log
        Log.d("addBook", message.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, message.getFirstname());
        values.put(COLUMN_LASTNAME, message.getLastname());
        values.put(COLUMN_MOBILENUMBER, message.getMobilenumber());
        values.put(COLUMN_OTP, message.getOtp());
        values.put(COLUMN_TIME,message.getTime());

        // 3. insert
        // "INSERT INTO BOOK (TITLE, AUTHOR) VALUES (book.title, book.author)"

        db.insert(TABLE_MESSAGES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public List<Messages> getAllMessages() {
        List<Messages>  messagesList = new LinkedList<Messages>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_MESSAGES +  " ORDER BY " + COLUMN_TIME +" DESC ";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build table and add it to list
        Messages messages = null;
        if (cursor.moveToFirst()) {
            do {
                messages = new Messages();
                messages.setId(Integer.parseInt(cursor.getString(0)));
                messages.setFirstname(cursor.getString(1));
                messages.setLastname(cursor.getString(2));
                messages.setMobilenumber(cursor.getString(3));
                messages.setOtp(cursor.getString(4));
                messages.setTime(cursor.getString(5));

                // Add book to books
                messagesList.add(messages);
            } while (cursor.moveToNext());
        }

        // return books
        return messagesList;
    }



}
