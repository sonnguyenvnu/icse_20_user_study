void onMediaButtonEvent(KeyEvent ev){
  if (ev.getKeyCode() == KeyEvent.KEYCODE_HEADSETHOOK || ev.getKeyCode() == KeyEvent.KEYCODE_MEDIA_PAUSE || ev.getKeyCode() == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE) {
    if (ev.getAction() == KeyEvent.ACTION_UP) {
      if (currentState == STATE_WAITING_INCOMING) {
        acceptIncomingCall();
      }
 else {
        setMicMute(!isMicMute());
        for (        StateListener l : stateListeners)         l.onAudioSettingsChanged();
      }
    }
  }
}
