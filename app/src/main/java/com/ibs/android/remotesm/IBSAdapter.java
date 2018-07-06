package com.ibs.android.remotesm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class IBSAdapter extends RecyclerView.Adapter<IBSAdapter.IBSViewHolder> {
    private Context mContext;
    private ArrayList<Item> mItemList;

    public IBSAdapter(Context context,ArrayList<Item> itemList)
    {
        mContext=context;
        mItemList=itemList;
    }

    @Override
    public IBSViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.item_view,parent,false);
        return new IBSViewHolder(v);
    }

    @Override
    public void onBindViewHolder(IBSViewHolder holder, int position) {
        Item item=mItemList.get(position);

        String label=item.getLabel();
        String icon=item.getIcon();
        String link=item.getLink();

        holder.mTextView.setText(label);
        holder.mTextViewLink.setText(link);
        //Picasso.with(mContext).load(icon).fit().centerInside().into(holder.mImageView);
        Picasso.with(mContext).load(icon).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class IBSViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView mImageView;
        public TextView mTextView;
        public TextView mTextViewLink;

        public IBSViewHolder(View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.imgIcon);
            mTextView=itemView.findViewById(R.id.tvLabel);
            mTextViewLink=itemView.findViewById(R.id.tvLink);
        }
    }

}
