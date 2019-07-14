private void fillMinusHitsFromResults(Set<ComperableHitResult> comperableHitResults){
  int currentId=1;
  List<SearchHit> minusHitsList=new ArrayList<>();
  for (  ComperableHitResult result : comperableHitResults) {
    ArrayList<Object> values=new ArrayList<Object>();
    values.add(result);
    SearchHit originalHit=result.getOriginalHit();
    SearchHit searchHit=new SearchHit(currentId,originalHit.getId(),new Text(originalHit.getType()),originalHit.getFields());
    searchHit.sourceRef(originalHit.getSourceRef());
    searchHit.getSourceAsMap().clear();
    Map<String,Object> sourceAsMap=result.getFlattenMap();
    for (    Map.Entry<String,String> entry : this.builder.getFirstTableFieldToAlias().entrySet()) {
      if (sourceAsMap.containsKey(entry.getKey())) {
        Object value=sourceAsMap.get(entry.getKey());
        sourceAsMap.remove(entry.getKey());
        sourceAsMap.put(entry.getValue(),value);
      }
    }
    searchHit.getSourceAsMap().putAll(sourceAsMap);
    currentId++;
    minusHitsList.add(searchHit);
  }
  int totalSize=currentId - 1;
  SearchHit[] unionHitsArr=minusHitsList.toArray(new SearchHit[totalSize]);
  this.minusHits=new SearchHits(unionHitsArr,totalSize,1.0f);
}
