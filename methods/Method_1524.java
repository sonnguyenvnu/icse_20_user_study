@Override public synchronized void onProducerFinishWithSuccess(String requestId,String producerName,@Nullable Map<String,String> extraMap){
  if (FLog.isLoggable(FLog.VERBOSE)) {
    Pair<String,String> mapKey=Pair.create(requestId,producerName);
    Long startTime=mProducerStartTimeMap.remove(mapKey);
    long currentTime=getTime();
    FLog.v(TAG,"time %d: onProducerFinishWithSuccess: " + "{requestId: %s, producer: %s, elapsedTime: %d ms, extraMap: %s}",currentTime,requestId,producerName,getElapsedTime(startTime,currentTime),extraMap);
  }
}
