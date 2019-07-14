protected SearchHit createUnmachedResult(List<Field> secondTableReturnedFields,int docId,String t1Alias,String t2Alias,SearchHit hit){
  String unmatchedId=hit.getId() + "|0";
  Text unamatchedType=new Text(hit.getType() + "|null");
  SearchHit searchHit=new SearchHit(docId,unmatchedId,unamatchedType,hit.getFields());
  searchHit.sourceRef(hit.getSourceRef());
  searchHit.getSourceAsMap().clear();
  searchHit.getSourceAsMap().putAll(hit.getSourceAsMap());
  Map<String,Object> emptySecondTableHitSource=createNullsSource(secondTableReturnedFields);
  mergeSourceAndAddAliases(emptySecondTableHitSource,searchHit,t1Alias,t2Alias);
  return searchHit;
}
