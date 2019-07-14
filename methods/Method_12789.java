public void removeCallback(Callback callback){
synchronized (mCallbacks) {
    mCallbacks.remove(callback);
  }
}
