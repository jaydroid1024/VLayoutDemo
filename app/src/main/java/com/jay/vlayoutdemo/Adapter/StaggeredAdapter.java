package com.jay.vlayoutdemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.jay.vlayoutdemo.R;

/**
 * Created by admin on 2017/5/16.
 */

public class StaggeredAdapter extends DelegateAdapter.Adapter {

    private Context context;
    private LayoutHelper helper;
    private LayoutInflater inflater;
    private String name;

    public StaggeredAdapter(Context context, LayoutHelper helper,String name) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.helper = helper;
        this.name=name;
    }

    public LayoutHelper onCreateLayoutHelper() {
        return helper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewholder(inflater.inflate(R.layout.layout_item, parent, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position%2==0){
            holder.itemView.setBackgroundColor(0xaa3F51B5);
        }else{
            holder.itemView.setBackgroundColor(0xccFF4081);
        }
        ViewGroup.LayoutParams layoutParams = ((MyViewholder) holder).text.getLayoutParams();
        layoutParams.height = 260 + position % 7 * 20;
        ((MyViewholder) holder).text.setLayoutParams(layoutParams);
        ((MyViewholder) holder).text.setText(position + 1 + "");
        MyViewholder myViewholder=(MyViewholder)holder;
        myViewholder.text.setText(name);

    }

    public int getItemCount() {
        return 60;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        private TextView text;
        public MyViewholder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.item_name);
        }
    }

}
