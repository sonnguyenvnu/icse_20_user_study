private void notifyPlayNext(Song song){
  for (  Callback callback : mCallbacks) {
    callback.onSwitchNext(song);
  }
}
