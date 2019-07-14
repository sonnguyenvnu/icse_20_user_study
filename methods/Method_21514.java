private Set<ComperableHitResult> simpleOneTimeQueryEach(){
  SearchHit[] firstTableHits=this.builder.getFirstSearchRequest().get().getHits().getHits();
  if (firstTableHits == null || firstTableHits.length == 0) {
    return new HashSet<>();
  }
  Set<ComperableHitResult> result=new HashSet<>();
  fillComperableSetFromHits(this.fieldsOrderFirstTable,firstTableHits,result);
  SearchHit[] secondTableHits=this.builder.getSecondSearchRequest().get().getHits().getHits();
  if (secondTableHits == null || secondTableHits.length == 0) {
    return result;
  }
  removeValuesFromSetAccordingToHits(this.fieldsOrderSecondTable,result,secondTableHits);
  return result;
}
