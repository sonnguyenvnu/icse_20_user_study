private HttpFile cache(){
  AggregatedHttpFile cachedFile=null;
  try {
    this.cachedFile=cachedFile=file.aggregate(MoreExecutors.directExecutor()).get();
  }
 catch (  Exception e) {
    this.cachedFile=null;
    logger.warn("Failed to cache a file: {}",file,Exceptions.peel(e));
  }
  return MoreObjects.firstNonNull(cachedFile,file);
}
