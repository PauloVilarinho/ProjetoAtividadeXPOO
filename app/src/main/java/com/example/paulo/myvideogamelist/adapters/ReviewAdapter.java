package com.example.paulo.myvideogamelist.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.paulo.myvideogamelist.R;
import com.example.paulo.myvideogamelist.ReviewFormActivity;
import com.example.paulo.myvideogamelist.models.Review;
import com.example.paulo.myvideogamelist.services.AuthService;
import com.example.paulo.myvideogamelist.services.DataBaseService;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    List<Review> reviewList;
    Context context;
    AuthService authService;
    DataBaseService dataBaseService;

    public ReviewAdapter(List<Review> reviewList, Context context, AuthService authService, DataBaseService dataBaseService) {
        this.reviewList = reviewList;
        this.context = context;
        this.authService = authService;
        this.dataBaseService = dataBaseService;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_review_layout,viewGroup,false);


        ReviewViewHolder viewHolder = new ReviewViewHolder(view);
        return viewHolder;
    }

    private void createDeleteDialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("You are going to delete that review. Are you sure?");
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataBaseService.deleteReview(reviewList.get(position));
                reviewList.remove(position);
                ReviewAdapter.this.notifyItemRemoved(position);
                ReviewAdapter.this.notifyItemRangeChanged(position,reviewList.size());
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder reviewViewHolder, final int i) {
        final Review review = reviewList.get(i);

        reviewViewHolder.reviewScore.setText(Integer.toString(review.getReviewNote()));
        reviewViewHolder.reviewText.setText(review.getText());
        reviewViewHolder.reviewAuthor.setText(review.getAuthor().getUsername());

        if (review.getAuthor().id == authService.getCurrentUser().id){
            reviewViewHolder.removeButton.setVisibility(View.VISIBLE);
            reviewViewHolder.removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createDeleteDialog(i);
                }
            });

            reviewViewHolder.editButton.setVisibility(View.VISIBLE);
            reviewViewHolder.editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ReviewFormActivity.class);
                    intent.putExtra("reviewid",review.id);
                    intent.putExtra("gameid",review.getTarget().id);
                    v.getContext().startActivity(intent);

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder{

        public TextView reviewAuthor;
        public TextView reviewScore;
        public TextView reviewText;
        public ImageButton removeButton;
        public ImageButton editButton;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);

            reviewAuthor = itemView.findViewById(R.id.gameReviewAuthor);
            reviewScore = itemView.findViewById(R.id.reviewScore);
            reviewText = itemView.findViewById(R.id.gameReviewText);
            removeButton = itemView.findViewById(R.id.removeReviewButton);
            editButton = itemView.findViewById(R.id.editReviewButton);

        }
    }
}
