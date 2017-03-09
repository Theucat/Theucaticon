package com.zxzq.car.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zxzq.car.R;
import com.zxzq.car.bean.JsonBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/6 0006.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {

    private List<JsonBean.ResultEntity.StationdesEntity> mResult;
    private boolean isLoadMore = false;

    public MyAdapter(List<JsonBean.ResultEntity.StationdesEntity> result){
        mResult =  result;
    }

    @Override
    public MyAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myViewHolder holder = new myViewHolder(View.inflate(parent.getContext(), R.layout.list_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.myViewHolder holder, int i) {
        holder.tv.setText(mResult.get(i).getName());
        holder.itemView.setTag(mResult.get(i));
    }

    @Override
    public int getItemCount() {
        return mResult.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public myViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_station);
        }
    }
}
