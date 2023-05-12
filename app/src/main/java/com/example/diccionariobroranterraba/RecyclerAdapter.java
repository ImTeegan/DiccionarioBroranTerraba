package com.example.diccionariobroranterraba;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private ArrayList<ImageAndSoundModel> modelArrayList;
    private Context context;
    private itemClickListener itemClickListener;

    public RecyclerAdapter(ArrayList<ImageAndSoundModel> modelArrayList, Context context,
                           itemClickListener itemClickListener) {
        this.modelArrayList = modelArrayList;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element, parent, false);
        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {

        if (modelArrayList.get(position).hasImage())
        {
            Glide.with(context)
                    .load(modelArrayList.get(position).getmImageResourceID())
                    .into(holder.imageView);
        }

        if (modelArrayList.get(position).hasImage())
        {
            Glide.with(context)
                    .load(modelArrayList.get(position).getmImageResourceID())
                    .into(holder.imageView);
            holder.imageView.setVisibility(View.VISIBLE);
        }
        else{
            holder.imageView.setVisibility(View.GONE);
        }

        holder.bindData(modelArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView, play;
        itemClickListener itemClickListener;

        TextView name;
        public ViewHolder(@NonNull View itemView, itemClickListener itemClickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            play = itemView.findViewById(R.id.play_button);
            name = itemView.findViewById(R.id.titleElement);
            this.itemClickListener = itemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemClickListener != null){
                itemClickListener.itemClick(getAdapterPosition());
            }

        }

        void bindData (final ImageAndSoundModel imageAndSoundModel) {
            name.setText(imageAndSoundModel.getName());
        }
    }

    //From this point, interface for item click
    public interface itemClickListener{
        void itemClick(int position);
    }
}
/*
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;

    public RecyclerAdapter(List<ListElement> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){return mData.size();} //cantidad de elementos en la lista

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_element, null);
        return new RecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListElement> items) { mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView name, city;

        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            name = itemView.findViewById(R.id.titleElement);
            city = itemView.findViewById(R.id.cityTextView);
        }

        void bindData (final ListElement item) {
            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            name.setText(item.getName());
            city.setText(item.getCity());
        }
    }
}
*/