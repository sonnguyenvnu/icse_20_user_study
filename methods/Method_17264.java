/** 
 * Returns the last node in the linked list. 
 */
@NonNull static <E>Node<E> findLast(@NonNull Node<E> node){
  Node<E> next;
  while ((next=node.getNextRelaxed()) != null) {
    node=next;
  }
  return node;
}
