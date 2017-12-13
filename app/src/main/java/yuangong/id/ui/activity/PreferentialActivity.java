package yuangong.id.ui.activity;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.Bind;
import butterknife.OnClick;
import yuangong.id.ui.activity.base.ListViewActivity;
import yuangong.id.utils.AppUtils;
import yuangong.id.AppConstant;
import yuangong.id.R;
import yuangong.id.adapter.ActivityAdapter;
import yuangong.id.bean.ActivityBean;
import yuangong.id.net.NetWorkUtils;
import yuangong.id.utils.JSONUtils;

public class PreferentialActivity extends ListViewActivity implements AdapterView.OnItemClickListener {


    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_text)
    TextView mTitleText;
    @Bind(R.id.listview)
    PullToRefreshListView mListview;
    private List<ActivityBean> mActivityBeanList;
    private ActivityAdapter mAdapter;

    @Override
    protected void bindAdapter() {
        mActivityBeanList = new ArrayList<>();
        mAdapter = new ActivityAdapter(context, mActivityBeanList);
        mListview.setAdapter(mAdapter);
        mListview.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        setPullRefreshListView(mListview, mAdapter);
        setADD();
    }



    //网络请求
    private void request() {
        HashMap<String, Object> params = new HashMap<>();
        NetWorkUtils.requestPHP(context, params, AppConstant.URL_ACTIVITY, new NetWorkUtils.NetWorkUtilsCallbackPHP() {
            @Override
            public void onError(int id) {
                NetWorkUtils.cacheMiss(context, id);
                onrequestDone();
            }

            @Override
            public void onWarn(String msg) {
                AppUtils.setToastMsg(context, msg);
                onrequestDone();
            }

            @Override
            public void onResponse(JSONObject t) {
                JSONArray jsonArray = t.optJSONArray("result");
                mActivityBeanList.clear();
                if (jsonArray == null) {
                    AppUtils.setToastMsg(context, "没有数据");
                    onrequestDone();
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                List<ActivityBean> beanList = JSONUtils.getBeanList(jsonArray, ActivityBean.class);
                onrequestDone();
                mActivityBeanList.addAll(beanList);
                mAdapter.notifyDataSetChanged();
            }
        });


    }


    @Override
    protected void initData() {
        mTitleBack.setVisibility(View.VISIBLE);
        mTitleText.setText("优惠活动");
    }

    @Override
    protected void setListener() {
        mListview.setOnItemClickListener(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_preferential;
    }

    @OnClick({R.id.title_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ActivityBean activityBean = mActivityBeanList.get(position - 1);
        Intent intent = new Intent(context,PreferentialDetailActivity.class);
        intent.putExtra("activity_name",activityBean.getActivity_name());
        //intent.putExtra("activity_starttime",activityBean.getActivity_starttime());
        intent.putExtra("activity_endtime",activityBean.getActivity_endtime());
        intent.putExtra("activity_description",activityBean.getActivity_description());
        startActivity(intent);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        request();
    }
}
