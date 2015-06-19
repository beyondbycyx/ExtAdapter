package com.example.hq.extadapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hq.extadapter.Vu.MainVu;
import com.example.hq.extadapter.adapters.Adapter;
import com.example.hq.extadapter.adapters.ControlAdapter;
import com.example.hq.extadapter.constant.ViewTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements MainVu<Map> {

    @InjectView(R.id.lv)
    ListView lv;
    @InjectView(R.id.addBt)
    Button addBt;
    private List<Map<String, String>> datas;

    @Override
    public void addItem(@NonNull ControlAdapter controlAdapter,@NonNull Map model) {
        controlAdapter.addItem(model);
    }

    @Override
    public void delItem(@NonNull ControlAdapter controlAdapter,@NonNull int position) {
        controlAdapter.removeItem(position);
    }

    @Override
    public void initVu(@NonNull Context mcontext, @LayoutRes int layoutResId) {

    }

    @Override
    public View getView() {
        return null;
    }

    @InjectView(R.id.delBt)
    Button delBt;

    private Adapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        final Map<String, String> m1 = new HashMap<String, String>() {{
            put("Title", "标题一");
            put("Content", "内容一");
        }};
        final Map<String, String> m2 = new HashMap<String, String>() {{
            put("Title", "标题二");
            put("Content", "内容二");
        }};
        final Map<String, String> m3 = new HashMap<String, String>() {{
            put("Title", "标题三");
            put("Content", "内容三");
        }};
         datas = new ArrayList<Map<String, String>>() {{

            add(m1);
            add(m2);
            add(m3);

        }};
        //添加监听
         myAdapter = new Adapter<Map<String, String>>(this, datas, R.layout.item_list) {
             @Override
             public void setListener(ViewHolder viewHolder) {
                 ((TextView) viewHolder.getView(R.id.content)).setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                     }
                 });
             }
             //绑定数据
             @Override
            public void bindData(Map<String, String> map, ViewHolder viewHolder) {
                ((TextView) viewHolder.getView(R.id.title)).setText(map.get("Title"));
                ((TextView) viewHolder.getView(R.id.content)).setText(map.get("Content"));
            }
        };

        lv.setAdapter(myAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Log.v("Tag---",position+"");
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final  int pos=position;
                builder.setMessage("确定删除吗？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //删除操作
                        delItem(myAdapter,pos);

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }
    @OnClick(R.id.addBt)
    public void onClickAdd(View v)
    {
        Map map=new HashMap();
        map.put("Title","add");
        map.put("Content","add");
        addItem(myAdapter, map);
    }
    @OnClick(R.id.delBt)
    public void onClickDel(View v)
    {
         delItem(myAdapter, datas.size() - 1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
