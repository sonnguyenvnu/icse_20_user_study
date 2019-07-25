@Override public SimpleErrorMessage check(){
  StringBuilder sb=new StringBuilder();
  for (  Redis redis : redises) {
    try {
      new PingCommand(pool.getKeyPool(new DefaultEndPoint(redis.getIp(),redis.getPort())),scheduled).execute().get();
      return SimpleErrorMessage.success();
    }
 catch (    InterruptedException|ExecutionException e) {
      logger.info("[check]",e);
      sb.append(String.format("%s: %s\r\n",redis.desc(),ExceptionUtils.getRootCause(e).getMessage()));
    }
  }
  return SimpleErrorMessage.fail(sb.toString());
}
