package com.example.hq.extadapter.Vu;

import android.support.annotation.NonNull;
import android.view.View;

import com.example.hq.extadapter.adapters.ControlAdapter;

/**
 * Created by hq on 2015/6/19.
 */
public interface MainVu<M> extends Vu {
    void addItem(@NonNull ControlAdapter controlAdapter,@NonNull M model);
    void delItem(@NonNull ControlAdapter controlAdapter,@NonNull int position);

}
