package eat.life.rosario.DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import eat.life.rosario.bean.Image;
import eat.life.rosario.bean.Note;

import android.net.Uri;

/**
 * Created by Rosario on 08/12/2014.
 */
public class NotesDBHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME="eatDBManager";
    private static final String NOTES_TABLE="notes";
    private static final String DIET_TABLE="diet";
    private static final String KEY_ID="id";
    private static final String KEY_NOTE="note";
    private static final String KEY_DATE="date";
    private static final String KEY_IMAGE_URI="imageUri";

    public NotesDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + NOTES_TABLE+" ("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +KEY_NOTE + " TEXT, "+KEY_DATE+" TEXT)");
        db.execSQL("CREATE TABLE " + DIET_TABLE+"  ("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +KEY_DATE + " TEXT, "+KEY_IMAGE_URI + " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NOTES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DIET_TABLE);
        onCreate(db);
    }

    public void createNote(Note note){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOTE,note.getNote());
        values.put(KEY_DATE, String.valueOf(note.getDate()));
       /* values.put(KEY_IMAGE_URI,note.getImageUri().toString());*/

        db.insert(NOTES_TABLE, null, values);
        db.close();
    }

    public void createDiet(Image image){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, String.valueOf(image.getDate()));
        values.put(KEY_IMAGE_URI,image.getImageUri().toString());

        db.insert(DIET_TABLE, null, values);
        db.close();
    }



    public Note getNote(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor;
        cursor = db.query(NOTES_TABLE, new String[] {KEY_ID, KEY_NOTE,KEY_DATE},KEY_ID+"=?", new String[]{String.valueOf(id)},null,null, null);

        Note note = null;
        if (cursor != null ) {
            cursor.moveToFirst();
            note = new Note(Integer.parseInt(cursor.getString(0)), cursor.getString(1),Date.valueOf(cursor.getString(2)));
        }
        db.close();
        cursor.close();
        return note;
    }

    public Image getDiet(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor;
        cursor = db.query(DIET_TABLE, new String[] {KEY_ID,KEY_DATE,KEY_IMAGE_URI},KEY_ID+"=?", new String[]{String.valueOf(id)},null,null,null);

        Image image = null;
        if (cursor != null ) {
            cursor.moveToFirst();
            image = new Image(Integer.parseInt(cursor.getString(0)),Date.valueOf(cursor.getString(1)) , Uri.parse(cursor.getString(2)));
        }
        db.close();
        cursor.close();
        return image;
    }

    public void deleteNote( Note note){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(NOTES_TABLE,KEY_ID+"=?",new String[]{String.valueOf(note.getId())});
        db.close();
    }

    public void deleteDiet( Image image){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DIET_TABLE,KEY_ID+"=?",new String[]{String.valueOf(image.getId())});
        db.close();
    }

    public int getNotesCount(){
        //SELECT COUNT(*) FROM notes
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+NOTES_TABLE,null);
        int count = cursor.getCount();
        db.close();
        cursor.close();
        return count;
    }

    public int getDietCount(){
        //SELECT COUNT(*) FROM diet
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+DIET_TABLE,null);
        int count = cursor.getCount();
        db.close();
        cursor.close();
        return count;
    }

    public int updateNote(Note note){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOTE,note.getNote());
        values.put(KEY_DATE, String.valueOf(note.getDate()));
       //values.put(KEY_IMAGE_URI,note.getImageUri().toString());
        return db.update(NOTES_TABLE,values, KEY_ID+"=?",new String[]{String.valueOf(note.getId())});
    }

    public int updateDiet(Image image){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, String.valueOf(image.getDate()));
        values.put(KEY_IMAGE_URI,image.getImageUri().toString());

        //values.put(KEY_IMAGE_URI,note.getImageUri().toString());
        return db.update(DIET_TABLE,values, KEY_ID+"=?",new String[]{String.valueOf(image.getId())});
    }

    public List<Note> getAllNotes(){
        List<Note> notetList = new ArrayList<Note>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM "+ NOTES_TABLE,null);
        if(cursor.moveToFirst()){
            do{
                System.out.println("El id de la nota desde la bd contiene " + cursor.getString(0));
                System.out.println("La nota desde la bd contiene " + cursor.getString(1));
                System.out.println("La fecha desde la base de datos contiene : " + cursor.getString(2));
                Note note = new Note(Integer.parseInt(cursor.getString(0)), cursor.getString(1),Date.valueOf(cursor.getString(2)));
                notetList.add(note);
            }while(cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return notetList;
    }

    public List<Image> getDiet(){
        List<Image> dietList = new ArrayList<Image>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM "+ DIET_TABLE,null);
        if(cursor.moveToFirst()){
            do{
                Image image = new Image(Integer.parseInt(cursor.getString(0)),Date.valueOf(cursor.getString(1)) , Uri.parse(cursor.getString(2)));
                dietList.add(image);
            }while(cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return dietList;
    }


}
