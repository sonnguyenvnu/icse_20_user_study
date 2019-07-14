@Override public boolean isEnded(){
  return !isInitialized() || (handledEndOfStream && !hasPendingData());
}
