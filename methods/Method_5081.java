@Override public void setCameraMotionListener(CameraMotionListener listener){
  verifyApplicationThread();
  cameraMotionListener=listener;
  for (  Renderer renderer : renderers) {
    if (renderer.getTrackType() == C.TRACK_TYPE_CAMERA_MOTION) {
      player.createMessage(renderer).setType(C.MSG_SET_CAMERA_MOTION_LISTENER).setPayload(listener).send();
    }
  }
}
