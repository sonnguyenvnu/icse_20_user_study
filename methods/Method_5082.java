@Override public void clearCameraMotionListener(CameraMotionListener listener){
  verifyApplicationThread();
  if (cameraMotionListener != listener) {
    return;
  }
  for (  Renderer renderer : renderers) {
    if (renderer.getTrackType() == C.TRACK_TYPE_CAMERA_MOTION) {
      player.createMessage(renderer).setType(C.MSG_SET_CAMERA_MOTION_LISTENER).setPayload(null).send();
    }
  }
}
