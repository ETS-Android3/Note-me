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

public class UserItemListAdapter extends ArrayAdapter<UserItem> {
    public UserItemListAdapter(Context context, ArrayList<UserItem> topicArrayList) {
        super(context ,R.layout.user_item, R.id.title, topicArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        UserItem t = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_item, parent, false);
        }

        TextView topic_name = convertView.findViewById(R.id.txtUser_name);
        TextView scorelbl = convertView.findViewById(R.id.txtUserScore);

        topic_name.setText("t.name");
        scorelbl.setText(t.score);

        return super.getView(position, convertView, parent);
    }
}
