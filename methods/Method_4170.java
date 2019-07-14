private void abandonAudioFocus(boolean forceAbandon){
  if (focusGain == C.AUDIOFOCUS_NONE && audioFocusState == AUDIO_FOCUS_STATE_NO_FOCUS) {
    return;
  }
  if (focusGain != C.AUDIOFOCUS_GAIN || audioFocusState == AUDIO_FOCUS_STATE_LOST_FOCUS || forceAbandon) {
    if (Util.SDK_INT >= 26) {
      abandonAudioFocusV26();
    }
 else {
      abandonAudioFocusDefault();
    }
    audioFocusState=AUDIO_FOCUS_STATE_NO_FOCUS;
  }
}
