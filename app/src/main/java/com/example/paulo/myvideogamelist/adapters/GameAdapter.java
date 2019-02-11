package com.example.paulo.myvideogamelist.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

import com.example.paulo.myvideogamelist.GameDetailsActivity;
import com.example.paulo.myvideogamelist.R;
import com.example.paulo.myvideogamelist.models.Game;
import com.example.paulo.myvideogamelist.services.DataBaseService;

import java.util.List;

import static com.example.paulo.myvideogamelist.models.ListGame_.game;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {


    List<Game> gameList;
    Context context;
    DataBaseService dataBaseService;
    OnClickListener onClickListener;
    OnLongClickListener onLongClickListener;


    public GameAdapter(List<Game> gameList, Context context, DataBaseService dataBaseService) {
        this.gameList = gameList;
        this.context = context;
        this.dataBaseService = dataBaseService;

    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_game_layout,viewGroup,false);

        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder gameViewHolder, int i) {

        final Game game = gameList.get(i);


        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GameDetailsActivity.class);
                intent.putExtra("gameid",game.id);
                v.getContext().startActivity(intent);
            }
        };

        onLongClickListener = new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        };

        gameViewHolder.gameDescription.setText(game.getDescription());
        gameViewHolder.gameTitle.setText(game.getTitle());
        gameViewHolder.view.setOnClickListener(onClickListener);




    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder{
        public TextView gameTitle;
        public TextView gameDescription;
        public CardView view;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);

            gameTitle = itemView.findViewById(R.id.gameItemTitleText);
            gameDescription = itemView.findViewById(R.id.gameItemDescriptionText);
            view = itemView.findViewById(R.id.gameCard);
        }
    }
}
