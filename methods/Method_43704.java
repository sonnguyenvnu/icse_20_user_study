@Override public void setResponseHeaders(Map<String,List<String>> headers){
  this.headers=headers;
  earliestTradeId=getHeaderAsLong("cb-after");
  latestTradeId=getHeaderAsLong("cb-before");
}
