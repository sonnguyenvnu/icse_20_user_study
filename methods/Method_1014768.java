public void start(){
  if (!started) {
    try {
      client=completableFuture.get(timeout,TimeUnit.MILLISECONDS);
    }
 catch (    Throwable t) {
      logger.error("Timeout! zookeeper server can not be connected in : " + timeout + "ms!",t);
      completableFuture.whenComplete(this::makeClientReady);
    }
    started=true;
  }
 else {
    logger.warn("Zkclient has already been started!");
  }
}
