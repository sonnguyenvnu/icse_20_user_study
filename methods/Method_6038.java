@Override protected void onStarted(){
  super.onStarted();
  droppedFrames=0;
  droppedFrameAccumulationStartTimeMs=SystemClock.elapsedRealtime();
  lastRenderTimeUs=SystemClock.elapsedRealtime() * 1000;
}
