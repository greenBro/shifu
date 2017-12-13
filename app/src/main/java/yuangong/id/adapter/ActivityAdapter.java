package yuangong.id.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import yuangong.id.R;
import yuangong.id.bean.ActivityBean;

/**
 * Created by Mathy on 2016/9/18 0018.
 * 描述：
 */
public class ActivityAdapter extends LGBaseAdapter<ActivityBean> {

    public ActivityAdapter(Context context, List<ActivityBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_newslist, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        ActivityBean resultBean = mDatas.get(position);
        holder.mItemActivity.setText(resultBean.getActivity_name());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_activity)
        TextView mItemActivity;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
