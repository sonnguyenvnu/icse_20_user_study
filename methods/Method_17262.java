/** 
 * Attempts to receive a linked list from a waiting producer or transfer the specified linked list to an arriving producer.
 * @param first the first node in the linked list to try to transfer
 * @param last the last node in the linked list to try to transfer
 * @return either {@code null} if the element was transferred, the first node if neither atransfer nor receive were successful, or the received last element from a producer
 */
@Nullable Node<E> transferOrCombine(@NonNull Node<E> first,Node<E> last){
  int index=index();
  AtomicReference<Node<E>> slot=arena[index];
  for (; ; ) {
    Node<E> found=slot.get();
    if (found == null) {
      if (slot.compareAndSet(null,first)) {
        for (int spin=0; spin < SPINS; spin++) {
          if (slot.get() != first) {
            return null;
          }
        }
        return slot.compareAndSet(first,null) ? first : null;
      }
    }
 else     if (slot.compareAndSet(found,null)) {
      last.lazySetNext(found);
      last=findLast(found);
      for (int i=1; i < ARENA_LENGTH; i++) {
        slot=arena[(i + index) & ARENA_MASK];
        found=slot.get();
        if ((found != null) && slot.compareAndSet(found,null)) {
          last.lazySetNext(found);
          last=findLast(found);
        }
      }
      return last;
    }
  }
}
