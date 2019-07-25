public ConceptSwitchIndexBuilder put(long uuidHigh,long uuidLow,long concept,int i){
  assert !myIsSealed;
  updateNextAvailableIndex(i);
  myConcepts.put(MetaIdFactory.conceptId(uuidHigh,uuidLow,concept),i);
  return this;
}
