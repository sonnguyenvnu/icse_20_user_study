@Override public boolean addAll(Collection<? extends E> c){
  requireNonNull(c);
  Node<E> first=null;
  Node<E> last=null;
  for (  E e : c) {
    requireNonNull(e);
    if (first == null) {
      first=factory.apply(e);
      last=first;
    }
 else {
      Node<E> newLast=new Node<>(e);
      last.lazySetNext(newLast);
      last=newLast;
    }
  }
  if (first == null) {
    return false;
  }
  append(first,last);
  return true;
}
