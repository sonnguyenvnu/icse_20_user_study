private void removeValuesFromSetAccordingToHits(String[] fieldsOrder,Set<ComperableHitResult> set,SearchHit[] hits){
  for (  SearchHit hit : hits) {
    ComperableHitResult comperableHitResult=new ComperableHitResult(hit,fieldsOrder,this.seperator);
    if (!comperableHitResult.isAllNull()) {
      set.remove(comperableHitResult);
    }
  }
}
