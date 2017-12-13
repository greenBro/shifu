package yuangong.id.ui.activity;

import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.Bind;
import yuangong.id.R;
import yuangong.id.ui.activity.base.ListViewActivity;
import yuangong.id.view.ClearEditText;

public class AllAccomplshActivity extends ListViewActivity {

    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_text)
    TextView mTitleText;
    @Bind(R.id.search_content)
    ClearEditText mSearchContent;
    @Bind(R.id.search)
    TextView mSearch;
    @Bind(R.id.spinner)
    Spinner mSpinner;
    @Bind(R.id.listview)
    PullToRefreshListView mListview;

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_all_accomplsh;
    }
}
