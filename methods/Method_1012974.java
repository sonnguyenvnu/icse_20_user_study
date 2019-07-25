/** 
 * Returns a synchronized type-specific indirect priority queue backed by the specified type-specific indirect priority queue, using an assigned object to synchronize.
 * @param q the indirect priority queue to be wrapped in a synchronized indirect priority queue.
 * @param sync an object that will be used to synchronize the access to the indirect priority queue.
 * @return a synchronized view of the specified indirect priority queue.
 */
public static <K>IndirectPriorityQueue<K> synchronize(final IndirectPriorityQueue<K> q,final Object sync){
  return new SynchronizedIndirectPriorityQueue<>(q,sync);
}
