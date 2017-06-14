package com.andrody.play_media;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.andrody.play_media.plus.Utilities;

import java.io.IOException;


/**
 * Created by Abboudi_Aliwi on 14.06.2017.
 * Website : http://andrody.com/
 * our channel on YouTube : https://www.youtube.com/c/Andrody2015
 * our page on Facebook : https://www.facebook.com/andrody2015/
 * our group on Facebook : https://www.facebook.com/groups/Programming.Android.apps/
 * our group on Whatsapp : https://chat.whatsapp.com/56JaImwTTMnCbQL6raHh7A
 * our group on Telegram : https://t.me/joinchat/AAAAAAm387zgezDhwkbuOA
 * Preview the app from Google play : https://play.google.com/store/apps/details?id=nasser_alqtami.andrody.com
 */

public class PlayerActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

	SeekBar seek_bar;
	ImageButton button_Play;
	ImageView image_Rhythm;
	TextView txt_Status,current_time,sound_duration;
	MediaPlayer media_voice;
	AnimationDrawable mAnimation;
	Handler mHandler = new Handler();
	Utilities utils;
	ProgressDialog pDialog;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player);

		media_voice = new MediaPlayer();

		processing_actionBar();
		linking_elements();

		utils = new Utilities();
		seek_bar.setOnSeekBarChangeListener(this);
		seek_bar.setMax(media_voice.getDuration());


		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) { // للتأكد من توفر الانترنت
			pDialog = ProgressDialog.show(PlayerActivity.this, "للأسف لا يوجد لديك اتصال بالانترنت", "اتصل بالانترنت ثم حاول مرة اخرى ...", true);
			pDialog.setCancelable(true);
			button_Play.setEnabled(false);
			seek_bar.setEnabled(false);
		}else {
			// التأكد من صلاحية الرابط
			if(URLUtil.isValidUrl(getIntent().getExtras().getString("url"))) {
				playSong();
			}else {
				Toast.makeText(this, getString(R.string.text_1), Toast.LENGTH_SHORT).show();
				button_Play.setEnabled(false);
				seek_bar.setEnabled(false);
			}
		}






		// برمجة زر التشغيل والإيقاف
		button_Play.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(media_voice.isPlaying()){
					if(media_voice!=null){
						media_voice.pause();
						stopRhythm();
						txt_Status.setText(getString(R.string.text_2));
						button_Play.setImageResource(R.drawable.img_btn_play);
					}
				}else{
					if(media_voice!=null){
						media_voice.start();
						startRhythm();
						txt_Status.setText(getString(R.string.text_3));
						button_Play.setImageResource(R.drawable.img_btn_pause);
					}
				}
			}
		});

	}

	public void processing_actionBar(){
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getSupportActionBar().setCustomView(R.layout.custom_actionbar);

		TextView mTitleTextView = (TextView) findViewById(R.id.title_text);
		mTitleTextView.setText(getIntent().getExtras().getString("name"));
	}

	public void linking_elements(){
		seek_bar = (SeekBar) findViewById(R.id.seekbar);
		txt_Status = (TextView) findViewById(R.id.songCurrentDurationLabel);
		current_time = (TextView) findViewById(R.id.songCurrentDurationLabel1);
		sound_duration = (TextView) findViewById(R.id.songTotalDurationLabel);
		button_Play = (ImageButton) findViewById(R.id.btnPlay);
		image_Rhythm=(ImageView) findViewById(R.id.img_equilizer);
		image_Rhythm.setBackgroundResource(R.drawable.simple_animation);
		mAnimation = (AnimationDrawable) image_Rhythm.getBackground();
	}



	public void  playSong(){
		try {
			media_voice.reset();
			media_voice.setAudioStreamType(AudioManager.STREAM_MUSIC);
			media_voice.setDataSource(getIntent().getExtras().getString("url"));
			txt_Status.setText(getString(R.string.text_4));

			media_voice.prepareAsync();
			media_voice.setOnPreparedListener(new OnPreparedListener() {
				@Override
				public void onPrepared(MediaPlayer mp) {
					mp.start();
					startRhythm();
					updateProgressBar();
					button_Play.setImageResource(R.drawable.img_btn_pause);
					txt_Status.setText(getString(R.string.text_3));
				}
			});

			media_voice.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					txt_Status.setText(getString(R.string.text_5));
					current_time.setText("");
					stopRhythm();
					button_Play.setImageResource(R.drawable.img_btn_play);
				}
			});

			button_Play.setImageResource(R.drawable.img_btn_pause);

			seek_bar.setProgress(0);
			seek_bar.setMax(100);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	private void startRhythm(){

		image_Rhythm.post(new Runnable(){
			public void run(){
				mAnimation.start();
			}
		});
	}

	private void stopRhythm(){
		image_Rhythm.post(new Runnable(){
			public void run(){
				mAnimation.stop();
			}
		});
	}

	public void updateProgressBar() {
		mHandler.postDelayed(mUpdateTimeTask, 100);
	}

	private Runnable mUpdateTimeTask = new Runnable() {
		public void run() {
			long totalDuration = media_voice.getDuration();
			long currentDuration = media_voice.getCurrentPosition();

			sound_duration.setText(""+utils.milliSecondsToTimer(totalDuration));
			current_time.setText(""+utils.milliSecondsToTimer(currentDuration));

			int progress = (int)(utils.getProgressPercentage(currentDuration, totalDuration));
			seek_bar.setProgress(progress);

			mHandler.postDelayed(this, 100);
		}
	};


	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		mHandler.removeCallbacks(mUpdateTimeTask);
	}


	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		mHandler.removeCallbacks(mUpdateTimeTask);
		int totalDuration = media_voice.getDuration();
		int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);

		media_voice.seekTo(currentPosition);

		updateProgressBar();
	}


	@Override
	public void onDestroy(){
		super.onDestroy();
		media_voice.release();
	}


	@Override
	public void onBackPressed() {
		mHandler.removeCallbacks(mUpdateTimeTask);
		seek_bar.setProgress(0);
		finish();
		super.onBackPressed();
	}

}