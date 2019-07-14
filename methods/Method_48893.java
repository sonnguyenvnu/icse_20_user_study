@Override public Comparator<JanusGraphElement> getSortOrder(){
  if (orders.isEmpty())   return new ComparableComparator();
 else   return orders;
}
