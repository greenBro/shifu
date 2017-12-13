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
import yuangong.id.bean.AccomplishBean;
import yuangong.id.bean.NotAccomplishBean;

/**
 * Created by Mathy on 2016/9/13 0013.
 * 描述：
 */
public class AccomplishAdapter extends LGBaseAdapter<AccomplishBean> {

    public AccomplishAdapter(Context context, List<AccomplishBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_onlineorderactivity_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        AccomplishBean accomplishBean = mDatas.get(position);

        holder.mItemName.setText(accomplishBean.getName() + "  (" + accomplishBean.getMobile() + ")");
        holder.mItemDetail.setText(accomplishBean.getCar_info_plate() + "  "+accomplishBean.getCar_info_park() );
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_name)
        TextView mItemName;
        @Bind(R.id.item_detail)
        TextView mItemDetail;
        @Bind(R.id.item_forward)
        ImageView mItemForward;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
