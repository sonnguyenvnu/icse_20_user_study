private void requestAudioFocus(){
  int result=AudioManagerCompat.requestAudioFocus(getAudioManager(),AudioManager.AUDIOFOCUS_GAIN,mAudioAttributes,mOnAudioFocusChangeListener);
  if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
    mPlayOnFocusGain=true;
    mOnAudioFocusChangeListener.onAudioFocusChange(AudioManager.AUDIOFOCUS_GAIN);
  }
 else {
    LogUtils.w("setPlayWhenReady(true) failed, cannot gain audio focus.");
  }
}
