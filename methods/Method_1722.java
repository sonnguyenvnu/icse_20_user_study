@Override public void onFetchCompletion(VolleyNetworkFetchState fetchState,int byteSize){
  fetchState.fetchCompleteTime=SystemClock.elapsedRealtime();
}
