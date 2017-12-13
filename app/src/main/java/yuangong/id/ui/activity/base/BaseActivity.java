package yuangong.id.ui.activity.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import yuangong.id.ui.broadcast.NetWorkBroadCastReceiver;
import yuangong.id.utils.ActivityCollector;
import yuangong.id.utils.AppUtils;
import yuangong.id.utils.JudgeNetWork;

public abstract class BaseActivity extends AppCompatActivity implements NetWorkBroadCastReceiver.netEventHandler {

    protected Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(getLayoutID());
        NetWorkBroadCastReceiver.mListeners.add(this);
        ButterKnife.bind((Activity) context);
        ActivityCollector.addActivity(this);
        initData();

        bindAdapter();

        setListener();
        
        netWork();

    }

    /**
     * 网络操作
     */
    protected void netWork() {
    }


    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置监听
     */
    protected abstract void setListener();

    /**
     * 绑定适配器
     */
    protected void bindAdapter() {

    }

    /**
     * 查找布局id
     *
     * @return
     */
    protected abstract int getLayoutID();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        finish();
    }

    @Override
    public void onNetChange() {
        if (JudgeNetWork.getNetWorkState(this) == JudgeNetWork.NETWORK_NONE) {
            AppUtils.setToastMsg(this,"当前网络不可用");
        }else {
            AppUtils.setToastMsg(this, "网络可以使用");
        }
    }
}
