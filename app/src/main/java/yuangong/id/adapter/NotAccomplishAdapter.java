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
import yuangong.id.bean.NotAccomplishBean;

/**
 * Created by Mathy on 2016/9/13 0013.
 * 描述：
 */
public class NotAccomplishAdapter extends LGBaseAdapter<NotAccomplishBean> {

    public NotAccomplishAdapter(Context context, List<NotAccomplishBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_onlineorderactivity_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NotAccomplishBean notAccomplishBean = mDatas.get(position);


        holder.mItemName.setText(notAccomplishBean.getName() + "  (" + notAccomplishBean.getMobile() + ")");

        holder.mItemDetail.setText(notAccomplishBean.getCar_info_plate() + "  " + notAccomplishBean.getCar_info_park());
        boolean isClear = notAccomplishBean.getIsClear();
        if (isClear){
            holder.mIsClear.setVisibility(View.VISIBLE);
        }else {
            holder.mIsClear.setVisibility(View.GONE);
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_name)
        TextView mItemName;
        @Bind(R.id.item_detail)
        TextView mItemDetail;
        @Bind(R.id.item_forward)
        ImageView mItemForward;
        @Bind(R.id.is_clear)
        TextView mIsClear;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
