@Override public Iterator<T> iterator(){
  return Iterables.filter(Iterables.transform(index,AbstractIndex.this::getObject),Objects::nonNull).iterator();
}
