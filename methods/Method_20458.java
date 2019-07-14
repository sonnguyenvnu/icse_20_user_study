@Override public synchronized boolean isReady(){
  return decoder != null && !decoder.isRecycled();
}
