void close(@Nullable Throwable throwable){
synchronized (buffer) {
    if (sinkClosed) {
      return;
    }
    sinkClosed=true;
    sinkClosedException=throwable;
    buffer.notifyAll();
  }
}
