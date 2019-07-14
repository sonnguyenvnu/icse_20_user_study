@Override public synchronized void onFailure(String id,Throwable throwable){
  final int numberOfListeners=mListeners.size();
  for (int i=0; i < numberOfListeners; ++i) {
    try {
      ControllerListener<? super INFO> listener=mListeners.get(i);
      if (listener != null) {
        listener.onFailure(id,throwable);
      }
    }
 catch (    Exception exception) {
      onException("InternalListener exception in onFailure",exception);
    }
  }
}
