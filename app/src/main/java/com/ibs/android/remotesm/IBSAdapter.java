package com.ibs.android.remotesm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class IBSAdapter extends RecyclerView.Adapter<IBSAdapter.IBSViewHolder> {
    private Context mContext;
    private ArrayList<Item> mItemList;
    private OnItemClickListener mListener;
    String[] kind={"switch","Switch"};
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

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
    public void onBindViewHolder(final IBSViewHolder holder, int position) {
        Item item=mItemList.get(position);

        String label=item.getLabel();
        String icon=item.getIcon();
        String link=item.getLink();
        String type=item.getType();
        String state=item.getState();
        Boolean check=false;
        if (state.equals("ON")) check=true;

        holder.mTextView.setText(label);
        holder.mTextViewLink.setText("");
        //Picasso.with(mContext).load(icon).fit().centerInside().into(holder.mImageView);
        Picasso.with(mContext).load(icon).into(holder.mImageView);
        holder.mSwitch.setChecked(check);
        holder.mSwitch.setText(state);
        Boolean visible=false;
        if (Arrays.asList(kind).contains(type)) visible=true;

        if (visible)holder.mSwitch.setVisibility(View.VISIBLE);
        else{
            holder.mSwitch.setVisibility(View.INVISIBLE);
        }

        holder.mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (holder.mSwitch.isChecked()==true)holder.mSwitch.setText("ON");
                else holder.mSwitch.setText("OFF");
            }
        });
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
        public Switch mSwitch;

        public IBSViewHolder(View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.imgIcon);
            mTextView=itemView.findViewById(R.id.tvLabel);
            mTextViewLink=itemView.findViewById(R.id.tvLink);
            mSwitch=itemView.findViewById(R.id.swControl);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
