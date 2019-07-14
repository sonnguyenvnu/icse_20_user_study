public static String hitsAsStringResult(SearchHits results,MetaSearchResult metaResults) throws IOException {
  if (results == null)   return null;
  Object[] searchHits;
  searchHits=new Object[(int)results.getTotalHits()];
  int i=0;
  for (  SearchHit hit : results) {
    HashMap<String,Object> value=new HashMap<>();
    value.put("_id",hit.getId());
    value.put("_type",hit.getType());
    value.put("_score",hit.getScore());
    value.put("_source",hit.getSourceAsMap());
    searchHits[i]=value;
    i++;
  }
  HashMap<String,Object> hits=new HashMap<>();
  hits.put("total",results.getTotalHits());
  hits.put("max_score",results.getMaxScore());
  hits.put("hits",searchHits);
  XContentBuilder builder=XContentFactory.contentBuilder(XContentType.JSON).prettyPrint();
  builder.startObject();
  builder.field("took",metaResults.getTookImMilli());
  builder.field("timed_out",metaResults.isTimedOut());
  builder.field("_shards",ImmutableMap.of("total",metaResults.getTotalNumOfShards(),"successful",metaResults.getSuccessfulShards(),"failed",metaResults.getFailedShards()));
  builder.field("hits",hits);
  builder.endObject();
  return BytesReference.bytes(builder).utf8ToString();
}
