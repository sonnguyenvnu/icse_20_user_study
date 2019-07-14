private void playInChatSound(){
  if (!inChatSoundEnabled || MediaController.getInstance().isRecordingAudio()) {
    return;
  }
  try {
    if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
      return;
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  try {
    SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
    int notifyOverride=getNotifyOverride(preferences,opened_dialog_id);
    if (notifyOverride == 2) {
      return;
    }
    notificationsQueue.postRunnable(() -> {
      if (Math.abs(System.currentTimeMillis() - lastSoundPlay) <= 500) {
        return;
      }
      try {
        if (soundPool == null) {
          soundPool=new SoundPool(3,AudioManager.STREAM_SYSTEM,0);
          soundPool.setOnLoadCompleteListener((soundPool,sampleId,status) -> {
            if (status == 0) {
              try {
                soundPool.play(sampleId,1.0f,1.0f,1,0,1.0f);
              }
 catch (              Exception e) {
                FileLog.e(e);
              }
            }
          }
);
        }
        if (soundIn == 0 && !soundInLoaded) {
          soundInLoaded=true;
          soundIn=soundPool.load(ApplicationLoader.applicationContext,R.raw.sound_in,1);
        }
        if (soundIn != 0) {
          try {
            soundPool.play(soundIn,1.0f,1.0f,1,0,1.0f);
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
