package yuangong.id.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import yuangong.id.R;
import yuangong.id.bean.PostageBean;

/**
 * Created by Mathy on 2016/9/13 0013.
 * 描述：
 */
public class PostageAdapter extends LGBaseAdapter<PostageBean> {

    public PostageAdapter(Context context, List<PostageBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_postageactivity_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        PostageBean postageBean = mDatas.get(position);
        holder.mItemName.setText(postageBean.getPackage_name());
        holder.mItemDetail.setText(postageBean.getPackage_discribe());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_name)
        TextView mItemName;
        @Bind(R.id.item_detail)
        TextView mItemDetail;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
