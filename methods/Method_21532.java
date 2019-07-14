private void fillInternalSearchHits(List<SearchHit> unionHits,SearchHit[] hits,Map<String,String> fieldNameToAlias){
  for (  SearchHit hit : hits) {
    SearchHit searchHit=new SearchHit(currentId,hit.getId(),new Text(hit.getType()),hit.getFields());
    searchHit.sourceRef(hit.getSourceRef());
    searchHit.getSourceAsMap().clear();
    Map<String,Object> sourceAsMap=hit.getSourceAsMap();
    if (!fieldNameToAlias.isEmpty()) {
      updateFieldNamesToAlias(sourceAsMap,fieldNameToAlias);
    }
    searchHit.getSourceAsMap().putAll(sourceAsMap);
    currentId++;
    unionHits.add(searchHit);
  }
}
