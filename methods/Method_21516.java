private void fillComperableSetFromHits(String[] fieldsOrder,SearchHit[] hits,Set<ComperableHitResult> setToFill){
  for (  SearchHit hit : hits) {
    ComperableHitResult comperableHitResult=new ComperableHitResult(hit,fieldsOrder,this.seperator);
    if (!comperableHitResult.isAllNull()) {
      setToFill.add(comperableHitResult);
    }
  }
}
