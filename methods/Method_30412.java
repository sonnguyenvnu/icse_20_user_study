private void abandonAudioFocus(){
  mPlayOnFocusGain=false;
  mPlayer.setPlayWhenReady(false);
  AudioManagerCompat.abandonAudioFocus(getAudioManager(),mOnAudioFocusChangeListener);
}
