package com.example.kakaobooksearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView bookListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText)findViewById(R.id.keywordEditText);
        Button searchBtn = (Button)findViewById(R.id.searchBtn);
        bookListView = (ListView)findViewById(R.id.bookListView);

        // anonymous inner class를 이용한 Event 처리
        // (Android의 전형적인 event처리방식)
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼을 눌렀을 때 서비스를 생성하고 실행.
                Intent i = new Intent();
                // 명시적 Intent를 사용.
                ComponentName cname = new ComponentName("com.example.kakaobooksearch",
                        "com.example.kakaobooksearch.KAKAOBookSearchService");
                i.setComponent(cname);
                i.putExtra("searchKeyword",editText.getText().toString());
                startService(i);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ArrayList<KAKAOBooKVO> result =
                intent.getParcelableArrayListExtra("resultData");
        Log.i("KAKAOBOOKLog","데이터가 정상적으로 Activity에 도달!!");

        KAKAOBookAdapter adapter = new KAKAOBookAdapter();
        bookListView.setAdapter(adapter);
        for(KAKAOBooKVO vo : result) {
            adapter.addList(vo);
        }

        // intent에서 데이터 추출해서 ListView에 출력하는 작업을 진행
        // 만약 그림까지 포함할려면 추가적인 작업이 더 들가야 해요.
        // ListView에 도서 제목만 일단 먼저 출력해 보고
        // 성공하면 CustomListView를 이용해서 이미지,제목,저자,가격 등의데이터를 출력
    }
}

