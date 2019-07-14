/** 
 * Links the element to the back of the deque so that it becomes the last element.
 * @param e the unlinked element
 */
void linkLast(final E e){
  final E l=last;
  last=e;
  if (l == null) {
    first=e;
  }
 else {
    setNext(l,e);
    setPrevious(e,l);
  }
}
