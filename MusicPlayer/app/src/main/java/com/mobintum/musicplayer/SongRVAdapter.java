package com.mobintum.musicplayer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rick on 17/10/15.
 */
public class SongRVAdapter extends RecyclerView.Adapter<SongRVAdapter.ViewHolder>{
    private List<Song> songs;
    private SetOnItemClickListener mListener;

    public SongRVAdapter(List<Song> songs, SetOnItemClickListener mListener) {
        this.songs = songs;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_song, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Song song = songs.get(position);
        holder.txtTitle.setText(song.getTitle());
        holder.txtAlbum.setText(song.getAlbum());
        holder.txtArtist.setText(song.getArtist());
        holder.txtTime.setText(song.getTime());
        holder.imgAlbum.setImageDrawable(song.getAlbumImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.itemClick(song);
            }
        });


    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView txtTitle;
        public final TextView txtAlbum;
        public final TextView txtArtist;
        public final TextView txtTime;
        public final ImageView imgAlbum;
        public final View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            this.txtAlbum = (TextView) itemView.findViewById(R.id.txtAlbum);
            this.txtArtist = (TextView) itemView.findViewById(R.id.txtArtist);
            this.txtTime = (TextView) itemView.findViewById(R.id.txtTime);
            this.imgAlbum = (ImageView) itemView.findViewById(R.id.imgAlbum);
            this.itemView = itemView;
        }

        public void setItemClickListener(View.OnClickListener listener){
            itemView.setOnClickListener(listener);
        }
    }


    public interface SetOnItemClickListener{
        public void itemClick(Song song);
    }

}
