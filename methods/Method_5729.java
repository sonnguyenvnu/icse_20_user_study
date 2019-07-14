@Override public Map<String,List<String>> getResponseHeaders(){
  return isReadingFromUpstream() ? upstreamDataSource.getResponseHeaders() : Collections.emptyMap();
}
