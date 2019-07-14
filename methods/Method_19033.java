private boolean shouldDispatchRequests(){
  return isLoadingCompleted() && !mWaitForDataBound;
}
