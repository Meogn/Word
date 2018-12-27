package com.example.word;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    String word;
    ListView listView;
    ArrayList<String> wordlist= new ArrayList<>();
    private static final String TAG = "SelectActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        ListView listView = (ListView) findViewById(R.id.list_view);
        Intent intent = getIntent();
        word = intent.getStringExtra("name");
        Log.d("AddActivity",word);
       Toast.makeText(this,word,Toast.LENGTH_SHORT).show();
        wordlist.add(word);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectActivity.this, android.R.layout.simple_list_item_1, wordlist);
        listView = (ListView) findViewById(R.id.list_view);

        listView.setAdapter(adapter);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.select,menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.delete://监听菜单按钮
//                AlertDialog.Builder dialog = new AlertDialog.Builder(SelectActivity.this);
//                dialog.setMessage("delete this word?");
//                dialog.setCancelable(false);
//                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        SQLiteDatabase db = dbHelper.getReadableDatabase();
//                        String[] where = {word};
//                        Log.d(TAG, "onClick: "+word);
//                        db.delete("Book","name=?",where);
////                        wordlist.remove(wordid);
//                        String sql = "delete from Book where name = ?";
//                        Toast.makeText(SelectActivity.this, "delete succeeded", Toast.LENGTH_SHORT).show();//提示删除成功
//                        wordlist.remove(word);
//                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectActivity.this,R.layout.support_simple_spinner_dropdown_item,wordlist);
//                        ListView listView = (ListView) findViewById(R.id.list_view);
//                        listView.setAdapter(adapter);
//
//                    }
//                });
//                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//                dialog.show();
//                break;
//            case R.id.update://监听菜单按钮
//                Intent intent = new Intent(SelectActivity.this, UpdateActivity.class);
//                startActivity(intent);
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
