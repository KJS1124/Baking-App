package com.example.bakingapp.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class RecipeStepAdapter extends BaseAdapter {


    private Context mContext;
    private List<String> steps;

    public RecipeStepAdapter(Context context, List<String> steps){
        this.mContext = context;
        this.steps = steps;
    }
    @Override
    public int getCount() {
        return steps.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = null;
        if(convertView == null){
            textView = new TextView(mContext);
            textView.setGravity(Gravity.CENTER);
        }
        else {
            textView = (TextView) convertView;
        }
        return textView;
    }
}
