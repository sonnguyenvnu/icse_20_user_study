protected void startRingtoneAndVibration(int chatID){
  SharedPreferences prefs=MessagesController.getNotificationsSettings(currentAccount);
  AudioManager am=(AudioManager)getSystemService(AUDIO_SERVICE);
  boolean needRing=am.getRingerMode() != AudioManager.RINGER_MODE_SILENT;
  if (needRing) {
    if (!USE_CONNECTION_SERVICE) {
      am.requestAudioFocus(this,AudioManager.STREAM_RING,AudioManager.AUDIOFOCUS_GAIN);
    }
    ringtonePlayer=new MediaPlayer();
    ringtonePlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
      @Override public void onPrepared(      MediaPlayer mediaPlayer){
        ringtonePlayer.start();
      }
    }
);
    ringtonePlayer.setLooping(true);
    ringtonePlayer.setAudioStreamType(AudioManager.STREAM_RING);
    try {
      String notificationUri;
      if (prefs.getBoolean("custom_" + chatID,false))       notificationUri=prefs.getString("ringtone_path_" + chatID,RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE).toString());
 else       notificationUri=prefs.getString("CallsRingtonePath",RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE).toString());
      ringtonePlayer.setDataSource(this,Uri.parse(notificationUri));
      ringtonePlayer.prepareAsync();
    }
 catch (    Exception e) {
      FileLog.e(e);
      if (ringtonePlayer != null) {
        ringtonePlayer.release();
        ringtonePlayer=null;
      }
    }
    int vibrate;
    if (prefs.getBoolean("custom_" + chatID,false))     vibrate=prefs.getInt("calls_vibrate_" + chatID,0);
 else     vibrate=prefs.getInt("vibrate_calls",0);
    if ((vibrate != 2 && vibrate != 4 && (am.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE || am.getRingerMode() == AudioManager.RINGER_MODE_NORMAL)) || (vibrate == 4 && am.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE)) {
      vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
      long duration=700;
      if (vibrate == 1)       duration/=2;
 else       if (vibrate == 3)       duration*=2;
      vibrator.vibrate(new long[]{0,duration,500},0);
    }
  }
}
