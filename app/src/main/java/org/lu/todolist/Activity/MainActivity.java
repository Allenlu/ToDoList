package org.lu.todolist.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.lu.todolist.R;
import org.lu.todolist.component.OnRefreshLayout;

public class MainActivity extends Activity  implements OnRefreshLayout.OnLoadListener {
    private OnRefreshLayout ll_refresh;
    private ListView lv_content;
    private Runnable refreshTask;
    private Runnable loadMoreTask;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ll_refresh=(OnRefreshLayout)findViewById(R.id.ll_refresh);
        ll_refresh.setOnRefreshListener(this);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        ll_refresh.setDistanceToTriggerSync(100);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        ll_refresh.setSize(SwipeRefreshLayout.LARGE); // 设置圆圈的大小

        refreshTask=new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Refresh data", Toast.LENGTH_SHORT).show();
                ll_refresh.setRefreshing(false);
            }
        };

        loadMoreTask=new Runnable(){
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,"load more Data",Toast.LENGTH_SHORT).show();
                ll_refresh.setLoading(false);
            }
        };
        handler=new Handler();


    }

    @Override
    public void onRefresh() {
        handler.postDelayed(refreshTask,8000);
    }

    @Override
    public void onPullUpToRefresh() {

    }
}
