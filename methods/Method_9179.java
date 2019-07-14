private void checkAudioFocus(){
  if (!hasAudioFocus) {
    AudioManager audioManager=(AudioManager)ApplicationLoader.applicationContext.getSystemService(Context.AUDIO_SERVICE);
    hasAudioFocus=true;
    if (audioManager.requestAudioFocus(this,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
      audioFocus=2;
    }
  }
}
