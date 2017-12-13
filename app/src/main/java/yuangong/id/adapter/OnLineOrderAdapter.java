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
import yuangong.id.bean.OnLineOrderBean;

/**
 * Created by Mathy on 2016/9/13 0013.
 * 描述：
 */
public class OnLineOrderAdapter extends LGBaseAdapter<OnLineOrderBean> {

    public OnLineOrderAdapter(Context context, List<OnLineOrderBean> datas) {
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
        OnLineOrderBean onLineOrderBean = mDatas.get(position);
        holder.mItemName.setText( onLineOrderBean.getName()+ "("+onLineOrderBean.getMobile() +")");
        holder.mItemDetail.setText(onLineOrderBean.getCar_info_plate() + "   "+ onLineOrderBean.getCar_info_park());
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
