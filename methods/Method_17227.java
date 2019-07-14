@Override @SuppressWarnings("deprecation") public void recordEviction(int weight){
  try {
    delegate.recordEviction(weight);
  }
 catch (  Throwable t) {
    logger.log(Level.WARNING,"Exception thrown by stats counter",t);
  }
}
