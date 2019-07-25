private List<T> removals(List<Entry<?,?>> items){
  return stream(halving(items.size()).spliterator(),false).map(i -> removeFrom(items,i)).flatMap(Collection::stream).map(this::convert).filter(this::inSizeRange).collect(toList());
}
