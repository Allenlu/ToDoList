package org.lu.todolist.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import org.lu.todolist.R;
import org.lu.todolist.component.OnRefreshLayout;
import org.lu.todolist.control.HttpRequest.ChanceRequest;
import org.lu.todolist.control.ImageAdapter;
import org.lu.todolist.control.tools.Task;

import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;

public class MainActivity extends Activity  implements SwipeRefreshLayout.OnRefreshListener,OnRefreshLayout.OnLoadListener {
    private OnRefreshLayout ll_refresh;
    private ListView lv_content;
    private ImageAdapter adapter;
    private long sTime;
    private long eTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        setContentView(R.layout.main);
        ll_refresh=(OnRefreshLayout)findViewById(R.id.ll_refresh);
        ll_refresh.setOnRefreshListener(this);
        ll_refresh.setOnLoadListener(this);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        ll_refresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        ll_refresh.setDistanceToTriggerSync(100);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        ll_refresh.setSize(SwipeRefreshLayout.LARGE); // 设置圆圈的大小

        adapter=new ImageAdapter(this);
        ListView listView = (ListView) findViewById(R.id.lv_content);
        listView.setAdapter(adapter);

        onRefresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onRefresh() {
        Map<String,Object> param=new HashMap<String,Object>();
        if(sTime!=0){
            param.put(ChanceRequest.PARAM_TIME,sTime);
            param.put(ChanceRequest.PARAM_FTYPE,0);
        }
        ChanceRequest refreshChance=new ChanceRequest(Task.GET_CHANCE);
        refreshChance.execute(param);
    }

    @Override
    public void onLoad(){
        Map<String,Object> param=new HashMap<String,Object>();
        param.put(ChanceRequest.PARAM_TIME,eTime);
        param.put(ChanceRequest.PARAM_FTYPE,1);
        ChanceRequest loadMoreChance=new ChanceRequest(Task.GET_CHANCE);
        loadMoreChance.execute(param);
    }

    public void onEvent(){

    }
}
