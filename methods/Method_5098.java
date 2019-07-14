private void sendVolumeToRenderers(){
  float scaledVolume=audioVolume * audioFocusManager.getVolumeMultiplier();
  for (  Renderer renderer : renderers) {
    if (renderer.getTrackType() == C.TRACK_TYPE_AUDIO) {
      player.createMessage(renderer).setType(C.MSG_SET_VOLUME).setPayload(scaledVolume).send();
    }
  }
}
