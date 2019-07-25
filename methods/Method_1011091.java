@Override public Iterator<IMapping<U,V>> iterator(){
  return new AbstractMapSequence.MappingIterator();
}
