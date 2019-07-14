@Override public synchronized void onProducerEvent(String requestId,String producerName,String producerEventName){
  if (FLog.isLoggable(FLog.VERBOSE)) {
    Pair<String,String> mapKey=Pair.create(requestId,producerName);
    Long startTime=mProducerStartTimeMap.get(mapKey);
    long currentTime=getTime();
    FLog.v(TAG,"time %d: onProducerEvent: {requestId: %s, stage: %s, eventName: %s; elapsedTime: %d ms}",getTime(),requestId,producerName,producerEventName,getElapsedTime(startTime,currentTime));
  }
}
