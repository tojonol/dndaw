package dnd.com.dndaw;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "legionDBr";

    // Contacts table name
    private static final String LEGION_TABLE = "legions";

    // Contacts Table Columns names
    private static final String KEY_ID = "title";
    private static final String URL = "name";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LEGION_TABLE = "CREATE TABLE " + LEGION_TABLE + "("
                + KEY_ID + " TEXT," + URL + " TEXT"+ ")";
        db.execSQL(CREATE_LEGION_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + LEGION_TABLE);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addLegion(Legion legion) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, legion.getTitle());
        values.put(URL, legion.getLink());

        // Inserting Row
        db.insert(LEGION_TABLE, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Legion getLegion(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(LEGION_TABLE, new String[] { KEY_ID,
                        URL }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Legion legion = new Legion(cursor.getString(0), cursor.getString(1));
        // return contact
        return legion;
    }

    // Getting All Contacts
    public List<Legion> getAllLegions() {
        List<Legion> legionList = new ArrayList<Legion>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + LEGION_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Legion legion = new Legion(cursor.getString(0),cursor.getString(1));
//                contact.setID(Integer.parseInt(cursor.getString(0)));
//                contact.setName(cursor.getString(1));
//                contact.setPhoneNumber(cursor.getString(2));
//                // Adding contact to list
//                contactList.add(contact);
                legionList.add(legion);
            } while (cursor.moveToNext());
        }

        // return contact list
        return legionList;
    }

    // Updating single contact
    public int updateContact(Legion legion) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
//        values.put(KEY_ID, legion.getTitle());
        values.put(URL, legion.getLink());

        // updating row
        return db.update(LEGION_TABLE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(legion.getTitle()) });
    }

    // Deleting single contact
    public void deleteLegion(Legion legion) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LEGION_TABLE, KEY_ID + " = ?",
                new String[] { String.valueOf(legion.getTitle()) });
        db.close();
    }


    // Getting legion Count
    public int getLegionsCount() {
        String countQuery = "SELECT  * FROM " + LEGION_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}