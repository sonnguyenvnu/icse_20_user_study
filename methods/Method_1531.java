@Override public synchronized void onRequestCancellation(String requestId){
  if (FLog.isLoggable(FLog.VERBOSE)) {
    Long startTime=mRequestStartTimeMap.remove(requestId);
    long currentTime=getTime();
    FLog.v(TAG,"time %d: onRequestCancellation: {requestId: %s, elapsedTime: %d ms}",currentTime,requestId,getElapsedTime(startTime,currentTime));
  }
}
