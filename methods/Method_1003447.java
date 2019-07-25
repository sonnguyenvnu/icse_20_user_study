/** 
 * Insert an entry at this index.
 * @param index the index
 * @param obj the object
 * @return the new immutable array
 */
public ImmutableArray2<K> insert(int index,K obj){
  int len=length + 1;
  int newLen=len;
  boolean extendable;
  if (index == len - 1) {
    AtomicBoolean x=canExtend;
    if (x != null) {
      canExtend=null;
      if (array.length > index && x.getAndSet(false)) {
        array[index]=obj;
        return new ImmutableArray2<>(array,len,true);
      }
    }
    extendable=true;
    newLen=len + 4;
  }
 else {
    extendable=false;
  }
  @SuppressWarnings("unchecked") K[] a2=(K[])new Object[newLen];
  DataUtils.copyWithGap(array,a2,length,index);
  a2[index]=obj;
  return new ImmutableArray2<>(a2,len,extendable);
}
