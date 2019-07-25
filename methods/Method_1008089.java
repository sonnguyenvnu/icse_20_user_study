@Override protected void cleanup(Runnable onCompletion){
  threadPool.generic().submit(() -> {
    try {
      client.close();
      logger.debug("Shut down remote connection");
    }
 catch (    IOException e) {
      logger.error("Failed to shutdown the remote connection",e);
    }
 finally {
      onCompletion.run();
    }
  }
);
}
