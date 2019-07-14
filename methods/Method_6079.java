@Override public void handleMessage(int messageType,@Nullable Object message) throws ExoPlaybackException {
  if (messageType == C.MSG_SET_CAMERA_MOTION_LISTENER) {
    listener=(CameraMotionListener)message;
  }
 else {
    super.handleMessage(messageType,message);
  }
}
