private void drain(Deque<Callback<None>> callbacks,Throwable t){
  for (; !callbacks.isEmpty(); ) {
    try {
      if (t != null) {
        callbacks.poll().onError(t);
      }
 else {
        callbacks.poll().onSuccess(None.none());
      }
    }
 catch (    Throwable throwable) {
      _log.error("Unexpected throwable from markUp/markDown callback.",throwable);
    }
  }
}
