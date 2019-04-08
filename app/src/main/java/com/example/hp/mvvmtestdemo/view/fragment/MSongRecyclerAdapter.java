package com.example.hp.mvvmtestdemo.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.mvvmtestdemo.R;
import com.example.hp.mvvmtestdemo.databinding.SongItemBinding;
import com.example.hp.mvvmtestdemo.model.song.Song;
import com.example.hp.mvvmtestdemo.viewmodel.ViewModelSong;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MSongRecyclerAdapter extends RecyclerView.Adapter<MSongRecyclerAdapter.MSViewHolder> {

    private Context mContext;
    private List<Song> list;        //该list应该提前初始化

    private OnMergeSongClickListener listener;

    public MSongRecyclerAdapter(Context context){
        this.mContext = context;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public MSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SongItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.song_item,parent,false);
        return new MSViewHolder(itemBinding,itemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MSViewHolder holder, final int position) {
        final Song song = list.get(position);

        ViewModelSong viewModelSong = new ViewModelSong(song);
        holder.binding.setViewModel(viewModelSong);

//        holder.binding.mergeSongTitle.setText(song.getTitle());
//        holder.binding.mergeSongArtist.setText(song.getArtist());
        holder.binding.mergeSongInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)listener.onClickItem(position);
            }
        });
        holder.binding.mergeSongMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopMenu(song,v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(@Nullable List<? extends Song> get) {
        list = (List<Song>) get;
    }

    public class MSViewHolder extends RecyclerView.ViewHolder {
        SongItemBinding binding ;


        public MSViewHolder(SongItemBinding binding,View itemView) {
            super(itemView);
            this.binding = binding;

        }
    }

    private void showPopMenu(final Song song, View anchor){
//        PopupMenu popupMenu = new PopupMenu(mContext,anchor);
//        popupMenu.getMenuInflater().inflate(R.menu.merge_song_pop_menu,popupMenu.getMenu());
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                if(listener != null){
//                    listener.onClickMenuItem(item.getItemId(),song);
//                }
//                return false;
//            }
//        });
//        makePopForceShowIcon(popupMenu);
//        popupMenu.show();
    }

    @SuppressLint("RestrictedApi")
    private void makePopForceShowIcon(PopupMenu popupMenu) {
        Field mFieldPopup = null;
        try {
            mFieldPopup = popupMenu.getClass().getDeclaredField("mPopup");
            mFieldPopup.setAccessible(true);
            MenuPopupHelper mPopup = (MenuPopupHelper) mFieldPopup.get(popupMenu);
            mPopup.setForceShowIcon(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public interface OnMergeSongClickListener{
        void onClickMenuItem(int itemId, Song song);
        void onClickItem(int position);
    }

    public void setOnClickListener(OnMergeSongClickListener listener){
        this.listener = listener;
    }

    public List<Song> getData(){
        return list;
    }

    public void updateData(List<Song> newList){
        if(newList==null){
            list.clear();
        }else {
            this.list = newList;
        }
    }
}
