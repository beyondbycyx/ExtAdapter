# ExtAdapter
BaseAdapter的扩展
#Adapter的示例
myAdapter = new Adapter<Map<String, String>>(this, datas, R.layout.item_list) 
  {
  
  
             //添加监听
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
<h2>ControlAdapter接口示例(对ListView里的item进行增删)</h2>
在Adapter里实现功能代码
在MainActivity中调用接口方法


