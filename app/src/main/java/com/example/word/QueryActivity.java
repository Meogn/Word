
package com.example.word;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class QueryActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    ListView listView;
    ArrayList<String> querylist= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        dbHelper = new MyDatabaseHelper(this,"Word.db",null,5);
        final Button query = (Button) findViewById(R.id.query);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                String sql = "select * from Book";
//                Cursor cursor = db.rawQuery(sql,new String[]{});
//               // Cursor cursor = db.query("Book",null,null,null,null,null,null);
//                if(cursor.moveToFirst()) {
//                    do{
//                        String name = cursor.getString(cursor.getColumnIndex("name"));
//                        String explanation = cursor.getString(cursor.getColumnIndex("explanation"));
//                        String example = cursor.getString(cursor.getColumnIndex("example"));
//                        Log.d("QueryActivity",name);
//                        Log.d("QueryActivity",explanation);
//                        Log.d("QueryActivity",example);
//                    }while (cursor.moveToNext());
//                }
//                cursor.close();

                ListView listView = (ListView)findViewById(R.id.list_view);
                EditText editText = (EditText) findViewById(R.id.input_message);
                String a = editText.getText().toString();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String[] where = {editText.getText().toString()};
                // Cursor cursor = db.query("WordTable",null,null,null,null,null,null);
                Cursor cursor = db.query("Book", new String[]{"name"}, "name=?", where, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String word = cursor.getString(cursor.getColumnIndex("name"));
//                        String word1 = cursor.getString(cursor.getColumnIndex("explanation"));
//                        String word2 = cursor.getString(cursor.getColumnIndex("example"));
//                        querylist.clear();
                        querylist.add(word);
//                        querylist.add(word1);
//                        querylist.add(word2);
                        Log.d("QueryActivity","aaa");
                    } while (cursor.moveToNext());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(QueryActivity.this, R.layout.support_simple_spinner_dropdown_item, querylist);
                listView.setAdapter(adapter);
            }
        });
    }
}