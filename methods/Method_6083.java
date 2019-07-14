private void resetListener(){
  lastTimestampUs=0;
  if (listener != null) {
    listener.onCameraMotionReset();
  }
}
