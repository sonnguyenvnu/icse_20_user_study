public void onAudioFocusChange(int focusChange){
  if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
    haveAudioFocus=true;
  }
 else {
    haveAudioFocus=false;
  }
}
