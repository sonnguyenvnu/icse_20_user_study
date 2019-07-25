@Override public Sort sort(){
  return new Sort.Builder().comparators(new ElementPropertyComparator.Builder().groups(getAnEdgeGroup()).property(getAnEntityPropertyName()).reverse(true).build()).resultLimit(20).deduplicate(true).build();
}
