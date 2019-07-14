@Override public synchronized void onRequestFailure(ImageRequest request,String requestId,Throwable throwable,boolean isPrefetch){
  if (FLog.isLoggable(FLog.WARN)) {
    Long startTime=mRequestStartTimeMap.remove(requestId);
    long currentTime=getTime();
    FLog.w(TAG,"time %d: onRequestFailure: {requestId: %s, elapsedTime: %d ms, throwable: %s}",currentTime,requestId,getElapsedTime(startTime,currentTime),throwable.toString());
  }
}
