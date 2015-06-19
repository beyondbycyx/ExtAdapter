package com.example.hq.extadapter.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.example.hq.extadapter.constant.ViewTag;

import java.util.List;

/**
 * Created by hq on 2015/6/18.
 */
/*
* 传入数据源和item的layout ,抽象方法为将数据源和layout匹配
* */
public abstract class  Adapter<M> extends BaseAdapter implements ControlAdapter<M>{
    private Context context;
    private int itemResId;
    private LayoutInflater layoutInflater;
    private ViewHolder viewHolder;
    private View.OnClickListener mListener;

    private List<M> mDatas;

    public ControlAdapter<M> controlAdapter;
    public Adapter(@NonNull Context mContext,@NonNull List<M> datas,@NonNull int itemResId) {
       this(mContext,datas,itemResId,null);

    }
    public  Adapter(@NonNull Context mContext,@NonNull List<M> datas,@NonNull int itemResId,@Nullable View.OnClickListener listener)
    {
        this.context=mContext;
        this.itemResId=itemResId;
        this.mDatas=datas;
        if (layoutInflater==null) {
            layoutInflater=LayoutInflater.from(mContext);
        }
        mListener=listener;
    }
    @Override
    public int getCount() {

        return  mDatas.size();
    }

    @Override
    public M getItem(int position) {

        return  mDatas==null? null:mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {

        return mDatas==null? null:position;
    }
    /**
     * 必须在此方法内inflater,convertview,且多少个itemView就要inflater多少次
     *
     * */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        viewHolder= ViewHolder.getDefaultInstance(convertView,layoutInflater,itemResId );
        //绑定数据
        bindData(getData(position),viewHolder);
        //添加监听
        setListener(viewHolder);
        convertView=viewHolder.getConvertView();

        return convertView;
    }
    //为子item中的某个组件设置监听
    public abstract void setListener(ViewHolder viewHolder);
    public abstract void bindData(M model,ViewHolder viewHolder);
    //获取数据
    public M getData(int position)
    {
        return this.mDatas.get(position);
    }
    /**
     * 对itemView的增删改
     *
     * */
    @Override
    public void addItem(int positon, M model) {
        mDatas.add(positon,model);
        notifyDataSetChanged();
    }

    @Override
    public void addItem(M model) {
        mDatas.add(model);
        notifyDataSetChanged();
    }

    @Override
    public int getDatasSize() {
        return mDatas==null?0:mDatas.size();
    }

    @Override
    public void removeItem(M model) {

    }

    @Override
    public void removeItem(int position) {
        mDatas.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public void setItem(int position, M model) {

    }

    /*
    * 实例：具有一个默认的实例，作为所有itemView的公用模板
    * 功能：根据id找出对应view
    * */
    public static class ViewHolder
    {
        //代替HashMap
        SparseArray<View> sparseArray;
        View mViews;
        View.OnClickListener itemOnClickListener;
        private static ViewHolder mViewHolderDef;
        public ViewHolder(@NonNull LayoutInflater layoutInflater,@LayoutRes int itemResId)
        {
            mViews=layoutInflater.inflate(itemResId,null);
            mViews.setTag(this);
            sparseArray=new SparseArray<View>();


        }
       //默认的单例
        public static  ViewHolder getDefaultInstance(@Nullable View convertView,@NonNull LayoutInflater layoutInflater,@LayoutRes int itemResId )
        {
            if (convertView == null) {
                 return new ViewHolder(layoutInflater, itemResId );

            }
            return (ViewHolder) convertView.getTag();

        }

        //用sparseMap获取view,为空则添加进去
        public View getView(int viewId)
        {
            View view=sparseArray.get(viewId);
            if (view==null) {
                view=mViews.findViewById(viewId);
                sparseArray.put(viewId,view);
            }
            return view;
        }
        public View getConvertView()
        {
            return mViews;
        }

    }
}
