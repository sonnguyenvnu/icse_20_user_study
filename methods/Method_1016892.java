public int location(Object entry){
  if (dictionary == null)   throw new IllegalStateException("This FeatureVector has no dictionary.");
  int i=dictionary.lookupIndex(entry,false);
  if (i < 0) {
    return -1;
  }
 else {
    return location(i);
  }
}
