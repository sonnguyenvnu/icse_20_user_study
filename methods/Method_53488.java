@Override public synchronized TwitterStream shutdown(){
  cleanUp();
synchronized (TwitterStreamImpl.class) {
    if (0 == numberOfHandlers) {
      if (dispatcher != null) {
        dispatcher.shutdown();
        dispatcher=null;
      }
    }
  }
  return this;
}
