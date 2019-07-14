public void insertIntoComparisonHash(String comparisonID,String comparisonKey,SearchHit hit){
  HashMap<String,SearchHitsResult> comparisonHash=this.comparisonIDtoComparisonHash.get(comparisonID);
  SearchHitsResult currentSearchHitsResult=comparisonHash.get(comparisonKey);
  if (currentSearchHitsResult == null) {
    currentSearchHitsResult=new SearchHitsResult(new ArrayList<SearchHit>(),false);
    comparisonHash.put(comparisonKey,currentSearchHitsResult);
  }
  currentSearchHitsResult.getSearchHits().add(hit);
}
