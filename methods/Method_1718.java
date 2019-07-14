@Override public void onFetchCompletion(OkHttpNetworkFetchState fetchState,int byteSize){
  fetchState.fetchCompleteTime=SystemClock.elapsedRealtime();
}
