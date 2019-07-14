@Override public Comparator getSortOrder(){
  return new RelationComparator(vertex,getOrders());
}
