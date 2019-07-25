@Override public IntIterator clone(){
  try {
    BufferReverseIntIteratorFlyweight x=(BufferReverseIntIteratorFlyweight)super.clone();
    if (this.iter != null) {
      x.iter=this.iter.clone();
    }
    return x;
  }
 catch (  CloneNotSupportedException e) {
    return null;
  }
}
