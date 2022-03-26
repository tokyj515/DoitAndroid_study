package org.techtown.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //데이터베이스를 헬퍼를 사용해서 만드는 방법
    //데이터베이스의 버전 정보, 이름 등을 넣어줘야함

    private static final String DATABASE_NAME = "person.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "person";
    public static final String PERSON_ID = "_id";
    public static final String PERSON_NAME = "name";
    public static final String PERSON_AGE = "age";
    public static final String PERSON_MOBILE = "mobile";

    public static final String[] ALL_COLUMNS = {PERSON_ID, PERSON_NAME, PERSON_AGE, PERSON_MOBILE};

    private static final String CREATE_TABLE =
            "create table "+TABLE_NAME+
                    " ("+PERSON_ID+" integer primary key autoincrement, " +
                        PERSON_NAME +" text, " +
                        PERSON_AGE+" integer, "+
                        PERSON_MOBILE+" text" +
                    ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) { //기존의 같은 이름의 데이터베이스가 없을 때 호출됨
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ TABLE_NAME);
        //지금은 테스트하는 과정이니까 바로 삭제하지만 실제로는 바로 삭제해선 안된다.
        onCreate(db);
    }
}
