package com.example.androidsample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySqliteHelper extends SQLiteOpenHelper {

    // 생성자도 다시 작성해 줘야 함
    public MySqliteHelper(Context context){
        // 상위 클래스의 생성자 호출(인자 4개짜리 생성자 호출)
        super(context, "Member.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 초기 데이터베이스 세팅코드가 위치
        // 테이블 생성하고 초기데이터 insert하는 작업
        // exeSQL() : resultset을 가져오지 않는 SQL구문 실행
        sqLiteDatabase.execSQL("create table if not exists member(userName TEXT, userAge INTEGER);");
        sqLiteDatabase.execSQL("insert into member values('홍길동', 30);");
        sqLiteDatabase.execSQL("insert into member values('최길동', 10);");
        sqLiteDatabase.execSQL("insert into member values('박길동', 50);");

        Log.i("DatabaseExam", "Helper의 onCreate() 호출");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
