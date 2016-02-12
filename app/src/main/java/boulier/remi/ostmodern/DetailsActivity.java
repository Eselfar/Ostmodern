package boulier.remi.ostmodern;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import boulier.remi.ostmodern.retrofit.Episode;

public class DetailsActivity extends AppCompatActivity {

    public static final String INTENT_CONTENT_EPISODE = "boulier.remi.ostmodern.intent.episode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        Episode episode = intent.getParcelableExtra(INTENT_CONTENT_EPISODE);

        TextView titleTV = (TextView) findViewById(R.id.details_title);
        if (episode != null)
            titleTV.setText(episode.getTitle());
    }
}
