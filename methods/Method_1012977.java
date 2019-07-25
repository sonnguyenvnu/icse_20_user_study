/** 
 * Returns a synchronized priority queue backed by the specified priority queue.
 * @param < K > the class of the objects in the queue.
 * @param q the priority queue to be wrapped in a synchronized priority queue.
 * @return a synchronized view of the specified priority queue.
 */
public static <K>PriorityQueue<K> synchronize(final PriorityQueue<K> q){
  return new SynchronizedPriorityQueue<>(q);
}
