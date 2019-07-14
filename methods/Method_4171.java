private int requestAudioFocusDefault(){
  return audioManager.requestAudioFocus(focusListener,Util.getStreamTypeForAudioUsage(Assertions.checkNotNull(audioAttributes).usage),focusGain);
}
