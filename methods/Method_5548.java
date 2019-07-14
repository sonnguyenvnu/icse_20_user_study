@Override public void flush(){
  queuedInputBufferCount=0;
  playbackPositionUs=0;
  while (!queuedInputBuffers.isEmpty()) {
    releaseInputBuffer(queuedInputBuffers.poll());
  }
  if (dequeuedInputBuffer != null) {
    releaseInputBuffer(dequeuedInputBuffer);
    dequeuedInputBuffer=null;
  }
}
