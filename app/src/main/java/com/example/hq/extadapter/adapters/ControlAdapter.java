package com.example.hq.extadapter.adapters;

import android.view.View;

/**
 * Created by hq on 2015/6/19.
 */
public interface ControlAdapter<M> {
    void addItem(int positon, M model);

    void addItem(M model);

    void removeItem(M model);

    void removeItem(int position);

    void setItem(int position, M model);
    int getDatasSize();
}
