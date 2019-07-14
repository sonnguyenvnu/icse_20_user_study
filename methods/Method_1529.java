@Override public synchronized void onRequestSuccess(ImageRequest request,String requestId,boolean isPrefetch){
  if (FLog.isLoggable(FLog.VERBOSE)) {
    Long startTime=mRequestStartTimeMap.remove(requestId);
    long currentTime=getTime();
    FLog.v(TAG,"time %d: onRequestSuccess: {requestId: %s, elapsedTime: %d ms}",currentTime,requestId,getElapsedTime(startTime,currentTime));
  }
}
