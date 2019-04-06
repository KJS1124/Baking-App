package com.example.bakingapp.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bakingapp.model.Step;

import org.w3c.dom.Text;

import java.util.List;

public class RecipeStepAdapter extends BaseAdapter {


    private Context mContext;
    private List<Step> steps;

    public RecipeStepAdapter(Context context, List<Step> steps){
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
            DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
            float dp = 80f;
            float fpixels = metrics.density * dp;
            int pixels = (int) (fpixels + 0.5f);
            textView.setHeight(pixels);
        }
        else {
            textView = (TextView) convertView;
        }

        textView.setText(steps.get(position).getShortDescription());
        return textView;
    }
}
