/** 
 * Only 1 iterator can be open at a time.
 * @return a closeable iterator of items.
 */
@Override public CloseableIterator<Element> iterator(){
  CloseableUtil.close(iterator);
  try {
    Set<EntitySeed> all=Sets.newHashSet(new EntitySeed());
    iterator=new ElementIterator(all.iterator());
  }
 catch (  final RetrieverException e) {
    LOGGER.error("{} returning empty iterator",e.getMessage(),e);
    return new EmptyCloseableIterator<>();
  }
  return iterator;
}
