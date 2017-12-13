package yuangong.id.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import yuangong.id.R;
import yuangong.id.bean.BecomeDueBean;
import yuangong.id.utils.AppUtils;

/**
 * Created by Mathy on 2016/9/13 0013.
 * 描述：
 */
public class BecomeDueAdapter extends LGBaseAdapter<BecomeDueBean> {

    public BecomeDueAdapter(Context context, List<BecomeDueBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_becomedueactivity_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        BecomeDueBean becomeDueBean = mDatas.get(position);
        holder.mItemName.setText(becomeDueBean.getName() + "(" +becomeDueBean.getMobile() +")") ;
        holder.mItemBecomeTime.setText(AppUtils.formatDate(becomeDueBean.getEnd_time()));
        holder.mItemTime.setText(becomeDueBean.getOrder_num()+"次");
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_name)
        TextView mItemName;
        @Bind(R.id.item_time)
        TextView mItemTime;
        @Bind(R.id.item_become_time)
        TextView mItemBecomeTime;
        @Bind(R.id.item_forward)
        ImageView mItemForward;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
