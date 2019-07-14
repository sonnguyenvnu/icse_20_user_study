/** 
 * Creates a queue with a linearizable backoff strategy. A thread waits for a completion signal if it successfully hands off the additional element(s) to another producing thread for batch insertion.
 * @param < E > the type of elements held in this collection
 * @return a new queue where producers wait for a completion signal after combining its additionwith another producing thread's
 */
public static <E>SingleConsumerQueue<E> linearizable(){
  return new SingleConsumerQueue<>(LinearizableNode<E>::new);
}
