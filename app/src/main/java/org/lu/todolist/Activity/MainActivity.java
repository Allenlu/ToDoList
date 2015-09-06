package org.lu.todolist.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.lu.todolist.R;
import org.lu.todolist.component.OnRefreshLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity  implements SwipeRefreshLayout.OnRefreshListener,OnRefreshLayout.OnLoadListener {
    private OnRefreshLayout ll_refresh;
    private ListView lv_content;
    private Runnable refreshTask;
    private Runnable loadMoreTask;
    private Handler handler;
    private BaseAdapter adapter;
    private  List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ll_refresh=(OnRefreshLayout)findViewById(R.id.ll_refresh);
        ll_refresh.setOnRefreshListener(this);
        ll_refresh.setOnLoadListener(this);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        ll_refresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,android.R.color.holo_orange_light, android.R.color.holo_red_light);
        ll_refresh.setDistanceToTriggerSync(100);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        ll_refresh.setSize(SwipeRefreshLayout.LARGE); // 设置圆圈的大小

        refreshTask=new Runnable() {
            @Override
            public void run() {
                datas.add(new Date().toGMTString());
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Refresh data", Toast.LENGTH_SHORT).show();
                ll_refresh.setRefreshing(false);
            }
        };

        loadMoreTask=new Runnable(){
            @Override
            public void run() {
                datas.add(new Date().toGMTString());
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"load more Data",Toast.LENGTH_SHORT).show();
                ll_refresh.setLoading(false);
            }
        };
        handler=new Handler();


        // 模拟一些数据
        datas = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            datas.add("item - " + i);
        }

        // 构造适配器
         adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datas);
        // 获取listview实例
        ListView listView = (ListView) findViewById(R.id.lv_content);
        listView.setAdapter(adapter);


    }

    @Override
    public void onRefresh() {
        handler.postDelayed(refreshTask,2000);
    }

    @Override
    public void onLoad(){
        handler.postDelayed(loadMoreTask,2000);
    }
}
