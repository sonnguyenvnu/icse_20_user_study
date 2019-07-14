@Override public boolean hasNext(){
  try {
    if (!queue.isEmpty()) {
      return true;
    }
    if (isFinished) {
      return false;
    }
    final ElasticSearchResponse res=client.search(scrollId);
    res.getResults().forEach(queue::add);
    isFinished=res.numResults() < batchSize;
    if (isFinished)     client.deleteScroll(scrollId);
    return res.numResults() > 0;
  }
 catch (  final IOException e) {
    throw new UncheckedIOException(e.getMessage(),e);
  }
}
