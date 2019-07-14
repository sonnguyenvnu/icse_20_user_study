private void checkAudioFocus(MessageObject messageObject){
  int neededAudioFocus;
  if (messageObject.isVoice() || messageObject.isRoundVideo()) {
    if (useFrontSpeaker) {
      neededAudioFocus=3;
    }
 else {
      neededAudioFocus=2;
    }
  }
 else {
    neededAudioFocus=1;
  }
  if (hasAudioFocus != neededAudioFocus) {
    hasAudioFocus=neededAudioFocus;
    int result;
    if (neededAudioFocus == 3) {
      result=NotificationsController.audioManager.requestAudioFocus(this,AudioManager.STREAM_VOICE_CALL,AudioManager.AUDIOFOCUS_GAIN);
    }
 else {
      result=NotificationsController.audioManager.requestAudioFocus(this,AudioManager.STREAM_MUSIC,neededAudioFocus == 2 ? AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK : AudioManager.AUDIOFOCUS_GAIN);
    }
    if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
      audioFocus=AUDIO_FOCUSED;
    }
  }
}
