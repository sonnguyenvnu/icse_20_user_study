@Override public int index(@Nullable SAbstractConcept c,int missingValue){
  if (c == null) {
    return missingValue;
  }
  SConceptId key=MetaIdHelper.getConcept(c);
  if (myConcepts.containsKey(key)) {
    return myConcepts.get(key);
  }
  return missingValue;
}
