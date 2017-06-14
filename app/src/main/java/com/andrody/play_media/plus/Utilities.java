package com.andrody.play_media.plus;


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

public class Utilities {

	public String milliSecondsToTimer(long milliseconds){
		String finalTimerString = "";
		String secondsString = "";
		
		   int hours = (int)( milliseconds / (1000*60*60));
		   int minutes = (int)(milliseconds % (1000*60*60)) / (1000*60);
		   int seconds = (int) ((milliseconds % (1000*60*60)) % (1000*60) / 1000);
		   if(hours > 0){
			   finalTimerString = hours + ":";
		   }
		   
		   if(seconds < 10){
			   secondsString = "0" + seconds;
		   }else{
			   secondsString = "" + seconds;}
		   
		   finalTimerString = finalTimerString + minutes + ":" + secondsString;
		
		return finalTimerString;
	}

	public int getProgressPercentage(long currentDuration, long totalDuration){
		Double percentage = (double) 0;
		
		long currentSeconds = (int) (currentDuration / 1000);
		long totalSeconds = (int) (totalDuration / 1000);
		
		percentage =(((double)currentSeconds)/totalSeconds)*100;
		
		return percentage.intValue();
	}

	public int progressToTimer(int progress, int totalDuration) {
		int currentDuration = 0;
		totalDuration = (int) (totalDuration / 1000);
		currentDuration = (int) ((((double)progress) / 100) * totalDuration);
		
		return currentDuration * 1000;
	}
}
