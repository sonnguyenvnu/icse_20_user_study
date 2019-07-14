@Override public void clearVideoFrameMetadataListener(VideoFrameMetadataListener listener){
  verifyApplicationThread();
  if (videoFrameMetadataListener != listener) {
    return;
  }
  for (  Renderer renderer : renderers) {
    if (renderer.getTrackType() == C.TRACK_TYPE_VIDEO) {
      player.createMessage(renderer).setType(C.MSG_SET_VIDEO_FRAME_METADATA_LISTENER).setPayload(null).send();
    }
  }
}
