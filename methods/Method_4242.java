@CallSuper @Override protected void onProcessedOutputBuffer(long presentationTimeUs){
  while (pendingStreamChangeCount != 0 && presentationTimeUs >= pendingStreamChangeTimesUs[0]) {
    audioSink.handleDiscontinuity();
    pendingStreamChangeCount--;
    System.arraycopy(pendingStreamChangeTimesUs,1,pendingStreamChangeTimesUs,0,pendingStreamChangeCount);
  }
}
