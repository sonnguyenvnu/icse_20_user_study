private void createKeyToResultsAndFillOptimizationStructure(Map<String,Map<String,List<Object>>> optimizationTermsFilterStructure,TableInJoinRequestBuilder firstTableRequest){
  List<SearchHit> firstTableHits=fetchAllHits(firstTableRequest);
  int resultIds=1;
  for (  SearchHit hit : firstTableHits) {
    HashMap<String,List<Map.Entry<Field,Field>>> comparisons=this.hashJoinComparisonStructure.getComparisons();
    for (    Map.Entry<String,List<Map.Entry<Field,Field>>> comparison : comparisons.entrySet()) {
      String comparisonID=comparison.getKey();
      List<Map.Entry<Field,Field>> t1ToT2FieldsComparison=comparison.getValue();
      String key=getComparisonKey(t1ToT2FieldsComparison,hit,true,optimizationTermsFilterStructure.get(comparisonID));
      SearchHit searchHit=new SearchHit(resultIds,hit.getId(),new Text(hit.getType()),hit.getFields());
      searchHit.sourceRef(hit.getSourceRef());
      onlyReturnedFields(searchHit.getSourceAsMap(),firstTableRequest.getReturnedFields(),firstTableRequest.getOriginalSelect().isSelectAll());
      resultIds++;
      this.hashJoinComparisonStructure.insertIntoComparisonHash(comparisonID,key,searchHit);
    }
  }
}
