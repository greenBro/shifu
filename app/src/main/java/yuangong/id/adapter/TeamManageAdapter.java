package yuangong.id.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import yuangong.id.R;
import yuangong.id.bean.StaffListBean;
import yuangong.id.view.SlidingMenu;

/**
 * Created by Mathy on 2016/9/13 0013.
 * 描述：
 */
public class TeamManageAdapter extends LGBaseAdapter<StaffListBean> {


    private boolean isVisible = false;

    public TeamManageAdapter(Context context, List<StaffListBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_teammanageactivity_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final StaffListBean staffListBean = mDatas.get(position);
        holder.mSlidingMenu.closeMenu();
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnDeleteStaffListener != null){
                    mOnDeleteStaffListener.deleteStaff(staffListBean.getStaff_id());
                }
            }
        });
        holder.mItemForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.teamTransfer(staffListBean.getStaff_id());
                }
            }
        });
        holder.mItemName.setText(staffListBean.getStaff_name());
        holder.mItemDetail.setText(staffListBean.getStaff_tel());
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.item_name)
        TextView mItemName;
        @Bind(R.id.item_detail)
        TextView mItemDetail;
        @Bind(R.id.item_forward)
        TextView mItemForward;
        @Bind(R.id.slidingmenu)
        SlidingMenu mSlidingMenu;
        @Bind(R.id.delete)
        TextView mDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 删除员工监听
     */
    private OnDeleteStaffListener mOnDeleteStaffListener;

    public void setOnDeleteStaffListener(OnDeleteStaffListener listener){
        mOnDeleteStaffListener = listener;
    }

    public interface OnDeleteStaffListener{
        void deleteStaff(String staffId);
    }

    /**
     *转移员工监听
     */
    private OnTeamTransferListener listener;

    public void setOnTeamTransferListener(OnTeamTransferListener listener) {
        this.listener = listener;
    }

    public interface OnTeamTransferListener {
        void teamTransfer(String uid);
    }

}
