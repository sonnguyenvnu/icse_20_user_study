@Override public boolean test(SAbstractConcept concept){
  return myTargetConcept == null || concept.isSubConceptOf(myTargetConcept);
}
