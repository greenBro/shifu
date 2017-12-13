package yuangong.id.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Mathy on 2016/8/23 0023.
 */
public abstract class LGBaseAdapter<T> extends BaseAdapter {
    protected List<T> mDatas;
    protected Context mContext;
    protected LayoutInflater mInflater;

    public LGBaseAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;
        this.mInflater = LayoutInflater.from(context);
    }

    /**
     * 更新数据
     * @param newData
     */
    public void update(List<T> newData){
        if (mDatas == null){
            return;
        }
        mDatas.clear();
        add(newData);
    }

    /**
     * 添加数据
     * @param extraData
     */
    public void add(List<T> extraData){
        mDatas.addAll(extraData);
        notifyDataSetChanged();
    }

    /**
     * 清楚所以数据
     */
    public void removeAll(){
        if (mDatas == null){
            Log.e("TGA","mDatas为空");
            return;
        }
        mDatas.clear();
        notifyDataSetChanged();
    }

    /**
     * 移除某一条数据
     * @param position
     */
    public void remove(int position){
        if (mDatas == null){
            Log.e("TGA","mDatas为空");
            return;
        }
        mDatas.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public final int getCount() {
        return mDatas.size();
    }

    @Override
    public final Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public final long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
