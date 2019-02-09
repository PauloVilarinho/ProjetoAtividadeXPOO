package com.example.paulo.myvideogamelist.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.paulo.myvideogamelist.ListFormActivity;
import com.example.paulo.myvideogamelist.R;
import com.example.paulo.myvideogamelist.models.GameList;
import com.example.paulo.myvideogamelist.models.User;
import com.example.paulo.myvideogamelist.services.DataBaseService;

import java.util.List;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameListViewHolder> {

    List<GameList> lists;
    Context context;
    DataBaseService dataBaseService;




    public GameListAdapter(DataBaseService dataBaseService, Context context, User user) {
        this.lists = dataBaseService.getAllListByUser(user);
        this.context = context;
        this.dataBaseService = dataBaseService;

    }

    private void createDeleteDialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("You are going to delete all data from that list. Are you sure?");
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataBaseService.deleteGameList(lists.get(position));
                lists.remove(position);
                GameListAdapter.this.notifyItemRemoved(position);
                GameListAdapter.this.notifyItemRangeChanged(position,lists.size());
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

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
    public void onBindViewHolder(@NonNull GameListViewHolder gameListViewHolder, final int i) {

        final GameList list = lists.get(i);

        gameListViewHolder.listTitle.setText(list.getTitulo());

        gameListViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDeleteDialog(i);
            }
        });

        gameListViewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListFormActivity.class);
                intent.putExtra("listid",lists.get(i).getId());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static class GameListViewHolder extends  RecyclerView.ViewHolder {

        public TextView listTitle;
        public RecyclerView gamesRV;
        public Button deleteButton;
        public Button editButton;
        public Button addGameButton;

        public GameListViewHolder(@NonNull View itemView) {
            super(itemView);

            listTitle = itemView.findViewById(R.id.listItemTitle);
            gamesRV = itemView.findViewById(R.id.gameRecyclerView);
            deleteButton = itemView.findViewById(R.id.deleteListButton);
            editButton = itemView.findViewById(R.id.editListButton);
            addGameButton = itemView.findViewById(R.id.addGameButton);

        }
    }

    public void setLists(List<GameList> lists) {
        this.lists = lists;
    }
}
