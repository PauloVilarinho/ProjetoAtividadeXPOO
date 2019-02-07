package com.example.paulo.myvideogamelist.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.paulo.myvideogamelist.R;
import com.example.paulo.myvideogamelist.models.GameList;

import java.util.List;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameListViewHolder> {

    List<GameList> lists;
    Context context;

    public GameListAdapter(List<GameList> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @NonNull
    @Override
    public GameListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(com.example.paulo.myvideogamelist.R.layout.item_list_layout,viewGroup,false);

        GameListViewHolder viewHolder = new GameListViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull GameListViewHolder gameListViewHolder, int i) {

        final GameList list = lists.get(i);

        gameListViewHolder.listTitle.setText(list.getTitulo());

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static class GameListViewHolder extends  RecyclerView.ViewHolder {

        public TextView listTitle;

        public GameListViewHolder(@NonNull View itemView) {
            super(itemView);

            listTitle = itemView.findViewById(R.id.listItemTitle);


        }
    }
}
