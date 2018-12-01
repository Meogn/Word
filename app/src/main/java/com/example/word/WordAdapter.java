package com.example.word;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Meo on 2018/11/29.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    private int resourceId;

    public WordAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Word> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        Word word = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView wordName = (TextView) view.findViewById(R.id.word);
        wordName.setText(word.getName());
        return view;
    }
}
