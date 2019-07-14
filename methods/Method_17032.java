/** 
 * Unlinks the non-null first element. 
 */
@SuppressWarnings("NullAway") E unlinkFirst(){
  final E f=first;
  final E next=getNext(f);
  setNext(f,null);
  first=next;
  if (next == null) {
    last=null;
  }
 else {
    setPrevious(next,null);
  }
  return f;
}
