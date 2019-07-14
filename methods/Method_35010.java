/** 
 * Queue that discards all elements.
 */
@Nullable @SuppressWarnings("unchecked") static <E>Queue<E> discardingQueue(){
  return (Queue)DISCARDING_QUEUE;
}
