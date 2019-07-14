private SearchHit getMergedHit(int currentCombinedResults,String t1Alias,String t2Alias,SearchHit hitFromFirstTable,SearchHit matchedHit){
  onlyReturnedFields(matchedHit.getSourceAsMap(),nestedLoopsRequest.getSecondTable().getReturnedFields(),nestedLoopsRequest.getSecondTable().getOriginalSelect().isSelectAll());
  SearchHit searchHit=new SearchHit(currentCombinedResults,hitFromFirstTable.getId() + "|" + matchedHit.getId(),new Text(hitFromFirstTable.getType() + "|" + matchedHit.getType()),hitFromFirstTable.getFields());
  searchHit.sourceRef(hitFromFirstTable.getSourceRef());
  searchHit.getSourceAsMap().clear();
  searchHit.getSourceAsMap().putAll(hitFromFirstTable.getSourceAsMap());
  mergeSourceAndAddAliases(matchedHit.getSourceAsMap(),searchHit,t1Alias,t2Alias);
  return searchHit;
}
