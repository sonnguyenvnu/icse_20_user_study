CompletableFuture<List<LogSegmentMetadata>> _XXXXX_(final long minTimestampToKeep){
  if (minTimestampToKeep >= Utils.nowInMillis()) {
    return FutureUtils.exception(new IllegalArgumentException("Invalid timestamp " + minTimestampToKeep + " to purge logs for "+ getFullyQualifiedName()));
  }
  return getCachedLogSegmentsAfterFirstFullFetch(LogSegmentMetadata.COMPARATOR).thenCompose(new Function<List<LogSegmentMetadata>,CompletableFuture<List<LogSegmentMetadata>>>(){
    @Override public CompletableFuture<List<LogSegmentMetadata>> apply(    List<LogSegmentMetadata> logSegments){
      List<LogSegmentMetadata> purgeList=new ArrayList<LogSegmentMetadata>(logSegments.size());
      int numCandidates=getNumCandidateLogSegmentsToPurge(logSegments);
      for (int iterator=0; iterator < numCandidates; iterator++) {
        LogSegmentMetadata l=logSegments.get(iterator);
        if ((l.isTruncated() || !conf.getExplicitTruncationByApplication()) && !l.isInProgress() && (l.getCompletionTime() < minTimestampToKeep)) {
          purgeList.add(l);
        }
 else {
          break;
        }
      }
      LOG.info("Deleting log segments older than {} for {} : {}",new Object[]{minTimestampToKeep,getFullyQualifiedName(),purgeList});
      return deleteLogSegments(purgeList);
    }
  }
);
}