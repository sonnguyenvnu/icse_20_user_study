protected void mergeSourceAndAddAliases(Map<String,Object> secondTableHitSource,SearchHit searchHit,String t1Alias,String t2Alias){
  Map<String,Object> results=mapWithAliases(searchHit.getSourceAsMap(),t1Alias);
  results.putAll(mapWithAliases(secondTableHitSource,t2Alias));
  searchHit.getSourceAsMap().clear();
  searchHit.getSourceAsMap().putAll(results);
}
