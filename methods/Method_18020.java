public void step(int numFrames){
  for (int i=0; i < numFrames; i++) {
    if (!mIsRunning) {
      return;
    }
    mCurrentTimeNanos+=FRAME_TIME_NANOS;
    mDataFlowGraph.doFrame(mCurrentTimeNanos);
    fireChoreographerCallbacks();
  }
}
