package com.example.noteme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TopicListAdapter extends ArrayAdapter<Topic> {
    public TopicListAdapter(Context context, ArrayList<Topic> topicArrayList) {
        super(context ,R.layout.topic_item, R.id.title, topicArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Topic t = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.topic_item, parent, false);
        }

        TextView topic_name = convertView.findViewById(R.id.topic_title);
        topic_name.setText(t.name);

        return super.getView(position, convertView, parent);
    }
}
