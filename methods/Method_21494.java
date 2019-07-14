public SearchHitsResult searchForMatchingSearchHits(String comparisonID,String comparisonKey){
  HashMap<String,SearchHitsResult> comparisonHash=this.comparisonIDtoComparisonHash.get(comparisonID);
  return comparisonHash.get(comparisonKey);
}
