@Override public boolean accept(RelationKind kind){
  return !kind.isComparable();
}
