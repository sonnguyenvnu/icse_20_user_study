public void markResponseFromCache(){
  HystrixThreadEventStream.getInstance().collapserResponseFromCache(collapserKey);
}
