@Override public SimpleImmutableEntry<String,Integer> visit(Leaf leaf){
  return new SimpleImmutableEntry<>(leaf.value(),0);
}
