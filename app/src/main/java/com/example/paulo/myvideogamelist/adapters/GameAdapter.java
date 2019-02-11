package com.example.paulo.myvideogamelist.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

import com.example.paulo.myvideogamelist.GameDetailsActivity;
import com.example.paulo.myvideogamelist.HomeActivity;
import com.example.paulo.myvideogamelist.R;
import com.example.paulo.myvideogamelist.models.Game;
import com.example.paulo.myvideogamelist.models.GameList;
import com.example.paulo.myvideogamelist.models.ListGame;
import com.example.paulo.myvideogamelist.services.DataBaseService;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {


    List<Game> games;
    Context context;
    DataBaseService dataBaseService;
    OnClickListener onClickListener;
    OnLongClickListener onLongClickListener;
    GameList gameList;



    public GameAdapter(List<Game> games, Context context, DataBaseService dataBaseService,GameList gameList) {
        this.games = games;
        this.context = context;
        this.dataBaseService = dataBaseService;
        this.gameList = gameList;

    }

    public GameAdapter(List<Game> gameList, Context context, DataBaseService dataBaseService) {
        this.games = gameList;
        this.context = context;
        this.dataBaseService = dataBaseService;

    }


    public void setGames(List<Game> games) {
        this.games = games;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_game_layout,viewGroup,false);

        return new GameViewHolder(view);
    }

    private void createDeleteDialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("You are going to remove this game from this list. Are you sure?");
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataBaseService.removeGameFromList(games.get(position),gameList);
                games.remove(position);
                GameAdapter.this.notifyItemRemoved(position);
                GameAdapter.this.notifyItemRangeChanged(position,games.size());
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder gameViewHolder, final int i) {

        final Game game = games.get(i);


        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GameDetailsActivity.class);
                intent.putExtra("gameid",game.id);
                v.getContext().startActivity(intent);
            }
        };


        gameViewHolder.gameDescription.setText(game.getDescription());
        gameViewHolder.gameTitle.setText(game.getTitle());
        gameViewHolder.view.setOnClickListener(onClickListener);

       if (context.getClass().equals(HomeActivity.class)){
           gameViewHolder.removeButton.setVisibility(View.VISIBLE);
           gameViewHolder.removeButton.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View v) {
                   createDeleteDialog(i);
               }
           });
       }




    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder{
        public TextView gameTitle;
        public TextView gameDescription;
        public CardView view;
        public ImageButton removeButton;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);

            gameTitle = itemView.findViewById(R.id.gameItemTitleText);
            gameDescription = itemView.findViewById(R.id.gameItemDescriptionText);
            view = itemView.findViewById(R.id.gameCard);
            removeButton = itemView.findViewById(R.id.removeGameButton);
        }
    }
}
