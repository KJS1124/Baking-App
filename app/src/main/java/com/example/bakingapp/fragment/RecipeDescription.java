package com.example.bakingapp.fragment;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
    private static final String PLAYER_PLAYBACK_POSITION = "player_position";
    private static final String PLAYER_CURRENT_WINDOW = "current_window";
    private static final String PLAYER_WHEN_READY = "when_ready";

    private SimpleExoPlayerView mExoPlayerView;
    private SimpleExoPlayer mExoPlayer;
    private TextView description;
    private Step step;
    private String path;
    private long playbackPosition;
    private int currentWindow;
    private Boolean playWhenReady;



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

        if(savedInstanceState != null){
            playbackPosition = savedInstanceState.getLong(PLAYER_PLAYBACK_POSITION);
            playWhenReady = savedInstanceState.getBoolean(PLAYER_WHEN_READY);
            currentWindow = savedInstanceState.getInt(PLAYER_CURRENT_WINDOW);
        }
        View view = inflater.inflate(R.layout.fragment_recipe_description, container, false);
        step = (Step) getArguments().getSerializable(STEP_DATA_KEY);
        Log.i("recipe description", "onCreateView: "+ step);
        mExoPlayerView = view.findViewById(R.id.player_view);
        description = view.findViewById(R.id.tv_step_description);

        description.setText(step.getDescription());
        mExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.question_mark));
        if (step.getVideoURL() != null)
            path = step.getVideoURL();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer(Uri.parse(step.getVideoURL()));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || mExoPlayer == null)) {
            initializePlayer(Uri.parse(step.getVideoURL()));
        }
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
            if(playWhenReady==null)
                mExoPlayer.setPlayWhenReady(true);
            else
                mExoPlayer.setPlayWhenReady(playWhenReady);
            if(currentWindow==0)
                mExoPlayer.seekTo(currentWindow,playbackPosition);
            else
                mExoPlayer.seekTo(0);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(PLAYER_PLAYBACK_POSITION,playbackPosition);
        outState.putInt(PLAYER_CURRENT_WINDOW,currentWindow);
        outState.putBoolean(PLAYER_WHEN_READY,playWhenReady);
    }

    private void releasePlayer() {
        if (mExoPlayer != null) {
            playbackPosition = mExoPlayer.getCurrentPosition();
            currentWindow = mExoPlayer.getCurrentWindowIndex();
            playWhenReady = mExoPlayer.getPlayWhenReady();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
