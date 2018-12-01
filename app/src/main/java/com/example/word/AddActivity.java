package com.example.word;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "ccc";
    private MyDatabaseHelper dbHelper;
    private ArrayList<String> wordlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Button submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);
        dbHelper = new MyDatabaseHelper(this,"Word.db",null,5);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                AlertDialog.Builder dialog = new AlertDialog.Builder(AddActivity.this);
                dialog.setMessage("add this word?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        SQLiteDatabase db = dbHelper.getWritableDatabase();
//                        ContentValues values = new ContentValues();
//                        values.put("name","mobile");
//                        values.put("explanation","moblie phone");
//                        values.put("example","this is a moblie phone");
//                        Log.d(TAG, "onClick: "+"success");
//                        db.insert("Book",null,values);
//                        values.clear();
                        EditText editText=(EditText)findViewById(R.id.word);
//                        EditText editText1=(EditText)findViewById(R.id.explanation);
//                        EditText editText2=(EditText)findViewById(R.id.example);
                        String word = editText.getText().toString();
//                        String explanation = editText.getText().toString();
//                        String example = editText.getText().toString();
                        Log.d(TAG, "onClick: "+word);
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        ContentValues values=new ContentValues();
                        values.put("name",word);
//                        values.put("explanation",explanation);
//                        values.put("example",example);
                        db.insert("Book",null,values);
//                        wordlist.add(word);
//                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddActivity.this,R.layout.support_simple_spinner_dropdown_item,wordlist);
//                        ListView listView = (ListView) findViewById(R.id.list_view);
//                        listView.setAdapter(adapter);

                    }
                });
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
                break;
            default:
                break;
        }
    }
}
