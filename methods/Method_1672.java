protected void handleFinalResult(PooledByteBufferOutputStream pooledOutputStream,FetchState fetchState){
  Map<String,String> extraMap=getExtraMap(fetchState,pooledOutputStream.size());
  ProducerListener listener=fetchState.getListener();
  listener.onProducerFinishWithSuccess(fetchState.getId(),PRODUCER_NAME,extraMap);
  listener.onUltimateProducerReached(fetchState.getId(),PRODUCER_NAME,true);
  notifyConsumer(pooledOutputStream,Consumer.IS_LAST | fetchState.getOnNewResultStatusFlags(),fetchState.getResponseBytesRange(),fetchState.getConsumer());
}
