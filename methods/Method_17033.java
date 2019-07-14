/** 
 * Unlinks the non-null last element. 
 */
@SuppressWarnings("NullAway") E unlinkLast(){
  final E l=last;
  final E prev=getPrevious(l);
  setPrevious(l,null);
  last=prev;
  if (prev == null) {
    first=null;
  }
 else {
    setNext(prev,null);
  }
  return l;
}
