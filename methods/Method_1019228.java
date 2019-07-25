/** 
 * API to de-queue entries to memory bounded queue
 * @param queue In Memory bounded queue
 */
public O consume(BoundedInMemoryQueue<?,I> queue) throws Exception {
  Iterator<I> iterator=queue.iterator();
  while (iterator.hasNext()) {
    consumeOneRecord(iterator.next());
  }
  finish();
  return getResult();
}
