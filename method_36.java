/** 
 * Links provided element as first element, or returns false if full.
 * @param e The element to link as the first element.
 * @return {@code true} if successful, otherwise {@code false}
 */
private boolean _XXXXX_(final E e){
  if (count >= capacity) {
    return false;
  }
  final Node<E> f=first;
  final Node<E> x=new Node<>(e,null,f);
  first=x;
  if (last == null) {
    last=x;
  }
 else {
    f.prev=x;
  }
  ++count;
  notEmpty.signal();
  return true;
}