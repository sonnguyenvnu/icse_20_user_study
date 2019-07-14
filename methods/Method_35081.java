private void guardAgainstEmptyCollection(Parsed v){
  if (v instanceof Collection && ((Collection)v).isEmpty()) {
    throw new IllegalStateException("empty result set");
  }
}
