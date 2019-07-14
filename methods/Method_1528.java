@Override public synchronized void onUltimateProducerReached(String requestId,String producerName,boolean successful){
  if (FLog.isLoggable(FLog.VERBOSE)) {
    Pair<String,String> mapKey=Pair.create(requestId,producerName);
    Long startTime=mProducerStartTimeMap.remove(mapKey);
    long currentTime=getTime();
    FLog.v(TAG,"time %d: onUltimateProducerReached: " + "{requestId: %s, producer: %s, elapsedTime: %d ms, success: %b}",currentTime,requestId,producerName,getElapsedTime(startTime,currentTime),successful);
  }
}
