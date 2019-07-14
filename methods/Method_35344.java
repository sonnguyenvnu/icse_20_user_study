private void notifyPlayStatusChanged(boolean isPlaying){
  for (  Callback callback : mCallbacks) {
    callback.onPlayStatusChanged(isPlaying);
  }
}
