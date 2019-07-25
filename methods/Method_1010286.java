public ConceptSwitchIndexBuilder put(SConceptId... cid){
  assert !myIsSealed;
  if (cid == null) {
    return this;
  }
  int i=myNextAvailableIndex;
  for (  SConceptId c : cid) {
    myConcepts.put(c,i++);
  }
  myNextAvailableIndex=i;
  return this;
}
