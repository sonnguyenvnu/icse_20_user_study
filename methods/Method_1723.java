@Override public Map<String,String> getExtraMap(VolleyNetworkFetchState fetchState,int byteSize){
  Map<String,String> extraMap=new HashMap<>(4);
  extraMap.put(QUEUE_TIME,Long.toString(fetchState.responseTime - fetchState.submitTime));
  extraMap.put(FETCH_TIME,Long.toString(fetchState.fetchCompleteTime - fetchState.responseTime));
  extraMap.put(TOTAL_TIME,Long.toString(fetchState.fetchCompleteTime - fetchState.submitTime));
  extraMap.put(IMAGE_SIZE,Integer.toString(byteSize));
  return extraMap;
}
