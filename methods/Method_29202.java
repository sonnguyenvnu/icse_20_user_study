public boolean invokeBgRewriteAof(final Jedis jedis){
  return new IdempotentConfirmer(){
    @Override public boolean execute(){
      try {
        String response=jedis.bgrewriteaof();
        if (response != null && response.contains("rewriting started")) {
          return true;
        }
      }
 catch (      Exception e) {
        String message=e.getMessage();
        if (message.contains("rewriting already")) {
          return true;
        }
        logger.error(message,e);
      }
      return false;
    }
  }
.run();
}
