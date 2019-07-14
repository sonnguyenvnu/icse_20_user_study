private void fillMinusHitsFromOneField(String fieldName,Set<Object> fieldValues,SearchHit someHit){
  List<SearchHit> minusHitsList=new ArrayList<>();
  int currentId=1;
  for (  Object result : fieldValues) {
    Map<String,DocumentField> fields=new HashMap<>();
    ArrayList<Object> values=new ArrayList<Object>();
    values.add(result);
    fields.put(fieldName,new DocumentField(fieldName,values));
    SearchHit searchHit=new SearchHit(currentId,currentId + "",new Text(someHit.getType()),fields);
    searchHit.sourceRef(someHit.getSourceRef());
    searchHit.getSourceAsMap().clear();
    Map<String,Object> sourceAsMap=new HashMap<>();
    sourceAsMap.put(fieldName,result);
    searchHit.getSourceAsMap().putAll(sourceAsMap);
    currentId++;
    minusHitsList.add(searchHit);
  }
  int totalSize=currentId - 1;
  SearchHit[] unionHitsArr=minusHitsList.toArray(new SearchHit[totalSize]);
  this.minusHits=new SearchHits(unionHitsArr,totalSize,1.0f);
}
