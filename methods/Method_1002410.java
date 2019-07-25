@Override public void shutdown(Callback<None> callback){
  _afterStartupCallbacks.add(() -> _wrappedZkStore.shutdown(callback));
  fireAfterStartupCallbacks();
}
