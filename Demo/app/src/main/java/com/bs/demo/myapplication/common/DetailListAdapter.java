package com.bs.demo.myapplication.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bs.demo.myapplication.R;

import java.util.List;


public class DetailListAdapter extends BaseAdapter {
    private Context context;

    private List<String> contentStrings;

    public DetailListAdapter(Context context, List<String> list) {
        this.context = context;

        this.contentStrings = list;
    }

    @Override
    public int getCount() {
        return contentStrings.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listitem_detail, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_item_content.setText(contentStrings.get(i));
        return view;
    }


    public class ViewHolder {
        public View rootView;

        public TextView tv_item_content;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_item_content = (TextView) rootView.findViewById(R.id.tv_item_content);
        }

    }
}



