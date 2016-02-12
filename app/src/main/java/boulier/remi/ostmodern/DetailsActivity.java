package boulier.remi.ostmodern;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import boulier.remi.ostmodern.model.EpisodeWrapper;
import boulier.remi.ostmodern.retrofit.Episode;

public class DetailsActivity extends AppCompatActivity {

    public static final String INTENT_CONTENT_EPISODE = "boulier.remi.ostmodern.intent.episode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        Episode episode = intent.getParcelableExtra(INTENT_CONTENT_EPISODE);

        if (episode == null) {
            Snackbar.make(findViewById(android.R.id.content), R.string.error_loading_episode, Snackbar.LENGTH_LONG)
                    .show();
            return;
        }

        final ImageView imageView = (ImageView) findViewById(R.id.details_image);
        TextView titleTV = (TextView) findViewById(R.id.details_title);
        TextView subtitleTV = (TextView) findViewById(R.id.details_subtitle);
        TextView synopsisTV = (TextView) findViewById(R.id.details_synopsis);
        TextView endsOnTV = (TextView) findViewById(R.id.details_ends_on);

        EpisodeWrapper wrapper = new EpisodeWrapper(episode);
        wrapper.getImageUrl(new EpisodeWrapper.OnEpisodeUpdate() {
            @Override
            public void onComplete(Episode episode) {
                Context context = DetailsActivity.this;
                Picasso.with(context)
                        .load(episode.getImageDetails().getUrl())
                        .placeholder(ContextCompat.getDrawable(context, R.drawable.image_placeholder_200px))
                        .error(ContextCompat.getDrawable(context, R.drawable.image_placeholder_200px))
                        .into(imageView);
            }
        });

        titleTV.setText(episode.getTitle());
        subtitleTV.setText(episode.getSubtitle());
        synopsisTV.setText(episode.getSynopsis());
        endsOnTV.setText(episode.getEndsOnFormated());
    }
}
