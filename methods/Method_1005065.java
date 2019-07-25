/** 
 * Only 1 iterator can be open at a time.
 * @return a closeable iterator of items.
 */
@Override public CloseableIterator<Element> iterator(){
  CloseableUtil.close(iterator);
  if (!hasSeeds()) {
    return new EmptyCloseableIterator<>();
  }
  if (readEntriesIntoMemory) {
    try {
      iterator=createElementIteratorReadIntoMemory();
    }
 catch (    final RetrieverException e) {
      LOGGER.error("{} returning empty iterator",e.getMessage(),e);
      return new EmptyCloseableIterator<>();
    }
  }
 else {
    try {
      iterator=createElementIteratorFromBatches();
    }
 catch (    final RetrieverException e) {
      LOGGER.error("{} returning empty iterator",e.getMessage(),e);
      return new EmptyCloseableIterator<>();
    }
  }
  return iterator;
}
