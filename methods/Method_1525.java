@Override public synchronized void onProducerFinishWithFailure(String requestId,String producerName,Throwable throwable,@Nullable Map<String,String> extraMap){
  if (FLog.isLoggable(FLog.WARN)) {
    Pair<String,String> mapKey=Pair.create(requestId,producerName);
    Long startTime=mProducerStartTimeMap.remove(mapKey);
    long currentTime=getTime();
    FLog.w(TAG,throwable,"time %d: onProducerFinishWithFailure: " + "{requestId: %s, stage: %s, elapsedTime: %d ms, extraMap: %s, throwable: %s}",currentTime,requestId,producerName,getElapsedTime(startTime,currentTime),extraMap,throwable.toString());
  }
}
