@Override public boolean isUnique(){
  return !index.isMixedIndex() && ((CompositeIndexType)index).getCardinality() == Cardinality.SINGLE;
}
