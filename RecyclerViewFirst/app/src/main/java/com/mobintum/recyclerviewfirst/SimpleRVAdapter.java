package com.mobintum.recyclerviewfirst;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rick on 16/10/15.
 */
public class SimpleRVAdapter extends RecyclerView.Adapter<SimpleRVAdapter.ViewHolder> {
    private List<Animal> dataSource;

    public SimpleRVAdapter(List<Animal> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_simple, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.text1 = (TextView) view.findViewById(R.id.text1);
        holder.image1 = (ImageView) view.findViewById(R.id.image1);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Animal animal = dataSource.get(position);
        holder.text1.setText(animal.getName());
        holder.image1.setImageDrawable(animal.getPhoto());

    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView text1;
        public ImageView image1;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
