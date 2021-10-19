package com.projectuas.songbird;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class FavoritOP {

    public static final String TAG = "Database favorit";

    SQLiteOpenHelper dbHandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            FavoritDB.COLUMN_ID,
            FavoritDB.COLUMN_TITLE,
            FavoritDB.COLUMN_SUBTITLE,
            FavoritDB.COLUMN_PATH
    };

    public FavoritOP(Context context) {
        dbHandler = new FavoritDB(context);
    }

    public void open() {
        Log.i(TAG, " Database dibuka");
        database = dbHandler.getWritableDatabase();
    }

    public void close() {
        Log.i(TAG, "Database ditutup");
        dbHandler.close();
    }

    public void addSongFav(ListLagu songsList) {
        open();
        ContentValues values = new ContentValues();
        values.put(FavoritDB.COLUMN_TITLE, songsList.getTitle());
        values.put(FavoritDB.COLUMN_SUBTITLE, songsList.getSubTitle());
        values.put(FavoritDB.COLUMN_PATH, songsList.getPath());

        database.insertWithOnConflict(FavoritDB.TABLE_SONGS, null, values, SQLiteDatabase.CONFLICT_REPLACE);

        close();
    }

    public ArrayList<ListLagu> getAllFavorites() {
        open();
        Cursor cursor = database.query(FavoritDB.TABLE_SONGS, allColumns,
                null, null, null, null, null);
        ArrayList<ListLagu> favSongs = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ListLagu songsList = new ListLagu(cursor.getString(cursor.getColumnIndex(FavoritDB.COLUMN_TITLE))
                        , cursor.getString(cursor.getColumnIndex(FavoritDB.COLUMN_SUBTITLE))
                        , cursor.getString(cursor.getColumnIndex(FavoritDB.COLUMN_PATH)));
                favSongs.add(songsList);
            }
        }
        close();
        return favSongs;
    }

    public void removeSong(String songPath) {
        open();
        String whereClause =
                FavoritDB.COLUMN_PATH + "=?";
        String[] whereArgs = new String[]{songPath};

        database.delete(FavoritDB.TABLE_SONGS, whereClause, whereArgs);
        close();
    }

}






















