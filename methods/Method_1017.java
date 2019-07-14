private void onFrameDropped(){
  mDroppedFrames++;
  if (FLog.isLoggable(FLog.VERBOSE)) {
    FLog.v(TAG,"Dropped a frame. Count: %s",mDroppedFrames);
  }
}
