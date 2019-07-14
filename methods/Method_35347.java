private void notifyComplete(Song song){
  for (  Callback callback : mCallbacks) {
    callback.onComplete(song);
  }
}
