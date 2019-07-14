private Iterator<E> convertIterator(Iterable<? extends JanusGraphProperty> iterable){
  if (getReturnType().forProperties()) {
    return (Iterator<E>)iterable.iterator();
  }
  assert getReturnType().forValues();
  return (Iterator<E>)Iterators.transform(iterable.iterator(),Property::value);
}
