/** 
 * {@inheritDoc}
 */
@Override public boolean contains(KType e){
  int fromIndex=head;
  int toIndex=tail;
  final KType[] buffer=Intrinsics.<KType[]>cast(this.buffer);
  for (int i=fromIndex; i != toIndex; i=oneRight(i,buffer.length)) {
    if (Intrinsics.equals(this,e,buffer[i])) {
      return true;
    }
  }
  return false;
}
