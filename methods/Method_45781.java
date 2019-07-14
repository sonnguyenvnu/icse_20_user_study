@Override public void checkStateChange(boolean originalState){
  if (originalState) {
    if (!isAvailable()) {
      notifyStateChangeToUnavailable();
    }
  }
 else {
    if (isAvailable()) {
      notifyStateChangeToAvailable();
    }
  }
}
