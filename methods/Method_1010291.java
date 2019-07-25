@Override public int index(SAbstractConcept c,int missingValue){
  if (c == null) {
    return missingValue;
  }
  long key=MetaIdHelper.getConcept(c).getIdValue();
  if (myIndex.containsKey(key)) {
    return myIndex.get(key);
  }
  return missingValue;
}
