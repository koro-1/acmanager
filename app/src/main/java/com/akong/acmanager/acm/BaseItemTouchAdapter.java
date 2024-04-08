package com.akong.acmanager.acm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.akong.acmanager.R;
import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

public abstract class BaseItemTouchAdapter extends RecyclerView.Adapter<BaseItemTouchAdapter.ViewHolder>  implements ItemTouchHelperAdapter{

    int viewRec;
 protected List<AccountInfo> data;
  protected   AccountManger accountManger;
 protected Context context;
    public BaseItemTouchAdapter(int viewRec, AccountManger accountManger) {
        this.viewRec = viewRec;
        this.accountManger = accountManger;
        context=accountManger.context;
    }

    public List<AccountInfo> getData() {
        return data;
    }

    public void setData(List<AccountInfo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public BaseItemTouchAdapter(int viewRec) {
        this.viewRec = viewRec;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onCreateViewHolderDefault(parent,viewType,0);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        onBindViewHolderDefault(holder,position,data.get(position));
    }
    protected  ViewHolder onCreateViewHolderDefault(ViewGroup parent, int viewType,int recId){
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.account_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    protected  void onBindViewHolderDefault(ViewHolder holder, int position,AccountInfo item){
        holder.textView.setText(item.getName());
        if (mOnItemClickLitener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView,item);
                }
            });


        }
        Glide.with(context).load(item.getImgUrl()).into(holder.imageView);
        holder.textView2.setText(item.getAccountId());

        AccountInfo loginAccountInfo = accountManger.getLoginAccountInfo();
        boolean flag= loginAccountInfo.getAccountId().equals(item.getAccountId());
        // 设置不可点击

        holder.textView3.setVisibility(flag?View.VISIBLE:View.INVISIBLE);

    }
    @Override
    public int getItemViewType(int position) {
        String accountId = accountManger.getLoginAccountInfo().getAccountId();
        if (data.get(position).getAccountId() .equals(accountId) ) {
            return -1; // 禁止滑动的 item 类型标识
        }
        return 0; // 其他正常可滑动的 item 类型标识
    }
   protected OnItemClickLitener<AccountInfo> mOnItemClickLitener;
    public void setOnItemClickLitener(OnItemClickLitener<AccountInfo> mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }



    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */


    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onItemDissmiss(int position) {
        //移除数据
        data.remove(position);
        accountManger.setAllAccounts(data);
        notifyItemRemoved(position);
    }

    /**
     * @param fromPosition
     * @param toPosition
     */
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //交换位置
        Collections.swap(data,fromPosition,toPosition);
        accountManger.setAllAccounts(data);
        notifyItemMoved(fromPosition,toPosition);
    }

    public  class ViewHolder extends RecyclerView.ViewHolder  {
        TextView textView;
        TextView textView2;
        TextView textView3;
        ImageView imageView;
        ImageView iv;
        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.textView2);
            imageView =     itemView.findViewById(R.id.imageView);
            textView2= itemView.findViewById(R.id.textView3);
            textView3= itemView.findViewById(R.id.textView4);
            tv= itemView.findViewById(R.id.tv_text);
            iv= itemView.findViewById(R.id.iv_img);

        }
    }
}
