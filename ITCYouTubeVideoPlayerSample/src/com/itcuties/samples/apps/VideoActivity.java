package com.itcuties.samples.apps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class VideoActivity extends Activity {

	private int REQ_PLAYER_CODE 	= 1;
	private static String YT_KEY 	= "YOUR-YOUTUBE-KEY-HERE";
	private static String VIDEO_ID 	= "sjOfxnlGAF4";	// Your video id here
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		
		Intent videoIntent = YouTubeStandalonePlayer.createVideoIntent(this, YT_KEY, VIDEO_ID, 0, true, false);
		
		startActivityForResult(videoIntent, REQ_PLAYER_CODE);
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQ_PLAYER_CODE && resultCode != RESULT_OK) {
			YouTubeInitializationResult errorReason = YouTubeStandalonePlayer.getReturnedInitializationResult(data);
			if (errorReason.isUserRecoverableError()) {
				errorReason.getErrorDialog(this, 0).show();
			} else {
				String errorMessage = String.format("PLAYER ERROR!!", errorReason.toString());
				Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
			}
		}
	}
}
