private Response wrap(SearchResponse response){
  List<SearchFailure> failures;
  if (response.getShardFailures() == null) {
    failures=emptyList();
  }
 else {
    failures=new ArrayList<>(response.getShardFailures().length);
    for (    ShardSearchFailure failure : response.getShardFailures()) {
      String nodeId=failure.shard() == null ? null : failure.shard().getNodeId();
      failures.add(new SearchFailure(failure.getCause(),failure.index(),failure.shardId(),nodeId));
    }
  }
  List<Hit> hits;
  if (response.getHits().getHits() == null || response.getHits().getHits().length == 0) {
    hits=emptyList();
  }
 else {
    hits=new ArrayList<>(response.getHits().getHits().length);
    for (    SearchHit hit : response.getHits().getHits()) {
      hits.add(new ClientHit(hit));
    }
    hits=unmodifiableList(hits);
  }
  return new Response(response.isTimedOut(),failures,response.getHits().getTotalHits(),hits,response.getScrollId());
}
