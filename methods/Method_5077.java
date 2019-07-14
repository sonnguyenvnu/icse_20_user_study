@Override public void setAudioAttributes(AudioAttributes audioAttributes,boolean handleAudioFocus){
  verifyApplicationThread();
  if (!Util.areEqual(this.audioAttributes,audioAttributes)) {
    this.audioAttributes=audioAttributes;
    for (    Renderer renderer : renderers) {
      if (renderer.getTrackType() == C.TRACK_TYPE_AUDIO) {
        player.createMessage(renderer).setType(C.MSG_SET_AUDIO_ATTRIBUTES).setPayload(audioAttributes).send();
      }
    }
    for (    AudioListener audioListener : audioListeners) {
      audioListener.onAudioAttributesChanged(audioAttributes);
    }
  }
  @AudioFocusManager.PlayerCommand int playerCommand=audioFocusManager.setAudioAttributes(handleAudioFocus ? audioAttributes : null,getPlayWhenReady(),getPlaybackState());
  updatePlayWhenReady(getPlayWhenReady(),playerCommand);
}
