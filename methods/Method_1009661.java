@Override public void invoke(IN input,Context context) throws Exception {
  Message msg=prepareMessage(input);
  if (batchFlushOnCheckpoint) {
    batchList.add(msg);
    if (batchList.size() >= batchSize) {
      flushSync();
    }
    return;
  }
  if (async) {
    try {
      producer.send(msg,new SendCallback(){
        @Override public void onSuccess(        SendResult sendResult){
          LOG.debug("Async send message success! result: {}",sendResult);
        }
        @Override public void onException(        Throwable throwable){
          if (throwable != null) {
            LOG.error("Async send message failure!",throwable);
          }
        }
      }
);
    }
 catch (    Exception e) {
      LOG.error("Async send message failure!",e);
    }
  }
 else {
    try {
      SendResult result=producer.send(msg);
      LOG.debug("Sync send message result: {}",result);
    }
 catch (    Exception e) {
      LOG.error("Sync send message failure!",e);
    }
  }
}
