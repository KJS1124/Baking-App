package com.example.bakingapp.fragment;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class RecipeDescription extends Fragment {

    private static final String STEP_DATA_KEY = "step_data";
    private SimpleExoPlayerView mExoPlayerView;
    private SimpleExoPlayer mExoPlayer;
    private TextView description;
    private Step step;


    public RecipeDescription() {
        // Required empty public constructor
    }


    public static RecipeDescription newInstance(Step step) {
        Log.i("RecipeDescription", "newInstance: ");
        RecipeDescription fragment = new RecipeDescription();
        Bundle args = new Bundle();
        args.putSerializable(STEP_DATA_KEY, step);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_recipe_description, container, false);
        step = (Step) getArguments().getSerializable(STEP_DATA_KEY);
        Log.i("recipe description", "onCreateView: "+ step);
        mExoPlayerView = view.findViewById(R.id.player_view);
        description = view.findViewById(R.id.tv_step_description);

        description.setText(step.getDescription());
        mExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.question_mark));
        if (step.getVideoURL() != null)
            initializePlayer(Uri.parse(step.getVideoURL()));
        return view;
    }

    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mExoPlayerView.setPlayer(mExoPlayer);
            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(getContext(), "Baking App");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
            mExoPlayer.seekTo(0);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }
}
