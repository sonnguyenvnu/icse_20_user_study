private void setPlayerVolume(){
  try {
    float volume;
    if (audioFocus != AUDIO_NO_FOCUS_CAN_DUCK) {
      volume=VOLUME_NORMAL;
    }
 else {
      volume=VOLUME_DUCK;
    }
    if (audioPlayer != null) {
      audioPlayer.setVolume(volume);
    }
 else     if (videoPlayer != null) {
      videoPlayer.setVolume(volume);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
