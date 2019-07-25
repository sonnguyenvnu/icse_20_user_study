@Override public void push(RowMap r) throws Exception {
  if (!r.shouldOutput(outputConfig)) {
    context.setPosition(r.getNextPosition());
    return;
  }
  String msg=r.toJSON(outputConfig);
  for (int cxErrors=0; cxErrors < 2; cxErrors++) {
    try {
      sendToRedis(msg);
      break;
    }
 catch (    Exception e) {
      if (e instanceof JedisConnectionException) {
        logger.warn("lost connection to server, trying to reconnect...",e);
        jedis.disconnect();
        jedis.connect();
      }
 else {
        this.failedMessageCount.inc();
        this.failedMessageMeter.mark();
        logger.error("Exception during put",e);
        if (!context.getConfig().ignoreProducerError) {
          throw new RuntimeException(e);
        }
      }
    }
  }
  if (r.isTXCommit()) {
    context.setPosition(r.getNextPosition());
  }
  if (logger.isDebugEnabled()) {
switch (redistype) {
case "lpush":
      logger.debug("->  queue:" + listkey + ", msg:" + msg);
    break;
case "pubsub":
default :
  logger.debug("->  channel:" + channel + ", msg:" + msg);
break;
}
}
}
