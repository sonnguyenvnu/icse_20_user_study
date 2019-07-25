/** 
 * Only 1 iterator can be open at a time.
 * @return a closeable iterator of items.
 */
@Override public CloseableIterator<EntityId> iterator(){
  CloseableUtil.close(iterator);
  if (!operation.getView().hasEdges()) {
    return new EmptyCloseableIterator<>();
  }
  final Iterator<? extends ElementId> idIterator=null != ids ? ids.iterator() : Collections.emptyIterator();
  if (!idIterator.hasNext()) {
    return new EmptyCloseableIterator<>();
  }
  try {
    iterator=new EntityIdIterator(idIterator);
  }
 catch (  final RetrieverException e) {
    LOGGER.error("{} returning empty iterator",e.getMessage(),e);
    return new EmptyCloseableIterator<>();
  }
  return iterator;
}
