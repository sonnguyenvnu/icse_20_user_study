void nack(final int nextRetryCount,final BaseMessage message){
  if (!completing.compareAndSet(false,true)) {
    return;
  }
  doSendNack(nextRetryCount,message);
}
