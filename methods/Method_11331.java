@Override public boolean isReady(){
  return decoder != null && !decoder.isRecycled();
}
