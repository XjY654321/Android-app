package com.bs.demo.myapplication.common;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.bs.demo.myapplication.R;

import java.util.List;


public class EdtListAdapter extends BaseAdapter {
    private Context context;

    private List<String> title;
    private List<String> content;

    public EdtListAdapter(Context context, List<String> title, List<String> content) {
        this.context = context;
        this.content = content;
        this.title = title;
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    public List<String> getContents(){
        return content;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listitem_edt, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_item_title.setText(title.get(i));
        viewHolder.edt_content.setText(content.get(i));
        viewHolder.edt_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                content.set(i,s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                content.set(i,s.toString());
            }
        });
        return view;
    }


    public static class ViewHolder {
        public View rootView;
        public TextView tv_item_title;
        public EditText edt_content;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_item_title = (TextView) rootView.findViewById(R.id.tv_item_title);
            this.edt_content = (EditText) rootView.findViewById(R.id.edt_content);
        }

    }
}



