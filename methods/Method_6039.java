@Override public void handleMessage(int messageType,@Nullable Object message) throws ExoPlaybackException {
  if (messageType == C.MSG_SET_SURFACE) {
    setSurface((Surface)message);
  }
 else   if (messageType == C.MSG_SET_SCALING_MODE) {
    scalingMode=(Integer)message;
    MediaCodec codec=getCodec();
    if (codec != null) {
      codec.setVideoScalingMode(scalingMode);
    }
  }
 else   if (messageType == C.MSG_SET_VIDEO_FRAME_METADATA_LISTENER) {
    frameMetadataListener=(VideoFrameMetadataListener)message;
  }
 else {
    super.handleMessage(messageType,message);
  }
}
