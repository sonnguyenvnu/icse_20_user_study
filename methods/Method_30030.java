public void stop(T writer){
  if (!remove(writer)) {
    throw new IllegalStateException("stop() called with unknown writer");
  }
}
