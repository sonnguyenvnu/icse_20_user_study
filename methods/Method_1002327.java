@Override public E poll(){
  long cIndex=this.cIndex;
  final long offset=calcElementOffset(cIndex);
  final E[] lb=buffer;
  final E e=lvElement(lb,offset);
  if (null == e) {
    return null;
  }
  soElement(lb,offset,null);
  this.cIndex=cIndex + 1;
  return e;
}
