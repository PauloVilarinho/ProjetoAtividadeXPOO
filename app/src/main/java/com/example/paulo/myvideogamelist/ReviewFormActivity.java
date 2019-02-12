package com.example.paulo.myvideogamelist;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.paulo.myvideogamelist.models.Game;
import com.example.paulo.myvideogamelist.models.Review;
import com.example.paulo.myvideogamelist.services.AuthService;

import io.objectbox.Box;

public class ReviewFormActivity extends AppCompatActivity {

    TextInputLayout reviewText;
    Spinner reviewNote;
    AuthService authService;
    App application;
    Box<Review> reviewBox;
    Box<Game> gameBox;
    long NEW_REVIEW_CODE = (long)12312.12232;
    long gameId;
    long reviewId;
    Review review;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_form);

        getIntentData();
        setupServices();
        setupBoxes();
        setupTextView();
        populatingSpinner();


    }

    private void getIntentData() {
        Intent intent = getIntent();
        gameId = intent.getLongExtra("gameid",(long)12312.2312);
        reviewId = intent.getLongExtra("reviewid",NEW_REVIEW_CODE);
    }

    private void setupServices() {
        application = (App)getApplication();
        authService = application.getAuthService();
    }

    private void setupBoxes() {
        reviewBox = application.getBoxStore().boxFor(Review.class);
        gameBox = application.getBoxStore().boxFor(Game.class);
    }

    private void setupTextView() {
        reviewText = findViewById(R.id.reviewText);
        reviewNote = findViewById(R.id.spinnerReviewNote);

        if (reviewId != NEW_REVIEW_CODE){
            review = reviewBox.get(reviewId);

            reviewText.getEditText().setText(review.getText());
            //TODO
            reviewNote.setSelection(review.getReviewNote()-1,false);
        }
    }

    private void populatingSpinner() {



        adapter = ArrayAdapter.createFromResource(this,R.array.note_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reviewNote.setAdapter(adapter);
    }

    public void submitReview(View view) {
        int note = reviewNote.getSelectedItemPosition() + 1;
        String newReviewtext = reviewText.getEditText().getText().toString();
        if (newReviewtext.equals("")){
            Toast.makeText(this, "Review field is empty. Please fill it.", Toast.LENGTH_SHORT).show();
        } else{

            if(reviewId != NEW_REVIEW_CODE){
                review.setText(newReviewtext);
                review.setReviewNote(note);
                reviewBox.put(review);
                finish();
            }else {
                Review review = new Review(newReviewtext, note, authService.getCurrentUser(), gameBox.get(gameId));
                reviewBox.put(review);
                finish();
            }
        }
    }
}
