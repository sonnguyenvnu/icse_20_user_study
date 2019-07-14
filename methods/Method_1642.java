@Override public void onFetchCompletion(HttpUrlConnectionNetworkFetchState fetchState,int byteSize){
  fetchState.fetchCompleteTime=mMonotonicClock.now();
}
