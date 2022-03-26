package org.techtown.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PersonProvider extends ContentProvider {

    public static final String AUTHORITY = "org.techtown.provider";
    public static final String BASE_PATH = "person";
    public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/"+BASE_PATH);

    public static final int PERSONS = 1;
    public static final int PERSON_ID = 2;
    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, PERSONS);
        uriMatcher.addURI(AUTHORITY, BASE_PATH+"/#", PERSON_ID);
    }


    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        DatabaseHelper helper = new DatabaseHelper(getContext());
        database = helper.getWritableDatabase();

        return true;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case PERSONS:
                return "vnd.android.cursor.dir/persons";
            default:
                throw new IllegalArgumentException("알 수 없는 URI: "+ uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long id = database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);

        if(id>0){
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("추가 실패 -> URI: "+uri);

    }

    @Nullable
    @Override
    //조회하는 함수
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;

        switch (uriMatcher.match(uri)){
            case PERSONS:
                cursor = database.query(DatabaseHelper.TABLE_NAME, DatabaseHelper.ALL_COLUMNS, selection, null, null,
                        null, DatabaseHelper.PERSON_NAME+" ASC");
                break;
            default:
                throw new IllegalArgumentException("알 수 없는 URI: "+uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case PERSONS:
                count = database.update(DatabaseHelper.TABLE_NAME, contentValues, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("알 수 없는 URI: "+uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case PERSONS:
                count = database.delete(DatabaseHelper.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("알 수 없는 URI: "+uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return count; //삭제된 레코드의 개수 반환환
    }





}
