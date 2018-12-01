package com.example.word;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    private ArrayList<String> wordlist = new ArrayList<>();
    ListView listView;
    private static final String TAG = "MainActivity";

//    private String[] data = {"apple","apple","apple"};

    @Override
    protected void onResume() {//刷新list
        wordlist.clear();
        Log.d(TAG, "onResume: "+111);
        SQLiteDatabase sd = dbHelper.getReadableDatabase();
        Cursor cursor = sd.rawQuery("select * from Book",null);
        while (cursor.moveToNext()) {

            String strword = cursor.getString(cursor.getColumnIndex("name"));
            wordlist.add(strword);
            Log.d(TAG, "onResume: "+strword);
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, wordlist);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String word = wordlist.get(position);
                Intent intent = new Intent(MainActivity.this, SelectActivity.class);
                intent.putExtra("name",word);
                startActivity(intent);
            }
        });
        registerForContextMenu(listView);
        super.onResume();
        ItemOnLongClick1();
    }
    private void ItemOnLongClick1() {

        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

                contextMenu.add(0,0,0,"delete");
                contextMenu.add(0,1,0,"update");
            }
        });

    }
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        getMenuInflater().inflate(R.menu.delete,menu);
//    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        int wordid = (int) info.id;
        switch (item.getItemId()){
            case 0:
                String word = wordlist.get(wordid);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String[] where = {word};
                Log.d(TAG, "onClick: "+word);
                db.delete("Book","name=?",where);
                wordlist.remove(wordid);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,wordlist);
                ListView listView = (ListView) findViewById(R.id.list_view);
                listView.setAdapter(adapter);
                break;
            case 1:
//                Intent intent = new Intent(MainActivity.this, QueryActivity.class);
//                startActivity(intent);
                final String[] where1={wordlist.get(wordid)};
                final int c=wordid;
                final EditText edit = new EditText(this);
//                 final EditText et = new EditText(this);
                //final String[] where1={date.get(MID)};
                //final int c=MID;
                edit.setText("");
                new AlertDialog.Builder(this).setTitle("请输入修改单词：")
                        .setIcon(android.R.drawable.sym_def_app_icon)
                        .setView(edit)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String a=edit.getText().toString();
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                ContentValues values=new ContentValues();
                                values.put("name",a);
                                db.update("Book",values,"name=?",where1);
                                wordlist.set(c,a);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,wordlist);
                                ListView listView = (ListView) findViewById(R.id.list_view);
                                listView.setAdapter(adapter);
                            }
                        }).setNegativeButton("取消",null).show();
                break;

        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDatabaseHelper(this,"Word.db",null,5);
//        Button createDatabase = (Button) findViewById(R.id.create_database);
//        createDatabase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dbHelper.getWritableDatabase();
//            }
//        });

//       ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);
       ListView listView = (ListView) findViewById(R.id.list_view);
//       listView.setAdapter(adapter);

//       initWords();
//       WordAdapter adapter = new WordAdapter(MainActivity.this,R.layout.activity_main,wordlist);
//       ListView listView = (ListView) findViewById(R.id.list_view);
//       listView.setAdapter(adapter);
//       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//           @Override
//           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//               Word word = wordlist.get(i);
//               Intent intent = new Intent(MainActivity.this, SelectActivity.class);
//               startActivity(intent);
//           }
//       });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String word = wordlist.get(position);
                Intent intent = new Intent(MainActivity.this, SelectActivity.class);
                intent.putExtra("name",word);
                startActivity(intent);

            }
        });

        SQLiteDatabase sd = dbHelper.getReadableDatabase();
        Cursor cursor = sd.rawQuery("select * from Book",null);
        while (cursor.moveToNext()){

            String strword = cursor.getString(cursor.getColumnIndex("name"));
            wordlist.add(strword);
//            String strwordmeaning= cursor.getString(cursor.getColumnIndex("wordmeaing"));
//            wordInfo st = new wordInfo(strword,strwordmeaning);
//            wordlist.add(wordInfo);

        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, wordlist);
        listView = (ListView) findViewById(R.id.list_view);

        listView.setAdapter(adapter);
    }

//   private void initWords(){
//        for(int i=0;i<3;i++) {
//            Word a = new Word("a","a","a");
//            wordlist.add(a);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_cart://监听菜单按钮
            Intent intent1 = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent1);
            break;
        case R.id.action:
            Intent intent2 = new Intent(MainActivity.this, QueryActivity.class);
            startActivity(intent2);
            break;
    }
        return super.onOptionsItemSelected(item);
    }

}
