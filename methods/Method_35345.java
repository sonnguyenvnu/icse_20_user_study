private void notifyPlayLast(Song song){
  for (  Callback callback : mCallbacks) {
    callback.onSwitchLast(song);
  }
}
