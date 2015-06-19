package com.example.hq.extadapter.Vu;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by hq on 2015/6/19.
 */
public interface Vu {

    void initVu(@NonNull Context mcontext,@LayoutRes int layoutResId);
    View getView();
}
