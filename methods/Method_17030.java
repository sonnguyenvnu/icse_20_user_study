/** 
 * Links the element to the front of the deque so that it becomes the first element.
 * @param e the unlinked element
 */
void linkFirst(final E e){
  final E f=first;
  first=e;
  if (f == null) {
    last=e;
  }
 else {
    setPrevious(f,e);
    setNext(e,f);
  }
}
