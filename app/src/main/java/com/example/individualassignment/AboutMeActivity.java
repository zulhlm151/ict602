package com.example.individualassignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutMeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView websiteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        websiteTextView = findViewById(R.id.websiteTextView);
        websiteTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == websiteTextView) {
            String githubUrl = "https://github.com/balminn/IndividualAssignment-ICT602";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));
            startActivity(intent);
        }
    }
}
