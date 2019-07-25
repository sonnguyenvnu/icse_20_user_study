/** 
 * Returns a synchronized priority queue backed by the specified priority queue, using an assigned object to synchronize.
 * @param < K > the class of the objects in the queue.
 * @param q the priority queue to be wrapped in a synchronized priority queue.
 * @param sync an object that will be used to synchronize the access to the priority queue.
 * @return a synchronized view of the specified priority queue.
 */
public static <K>PriorityQueue<K> synchronize(final PriorityQueue<K> q,final Object sync){
  return new SynchronizedPriorityQueue<>(q,sync);
}
