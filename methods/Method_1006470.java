@Override public IntIterator clone(){
  try {
    ReverseIntIteratorFlyweight x=(ReverseIntIteratorFlyweight)super.clone();
    if (this.iter != null) {
      x.iter=this.iter.clone();
    }
    return x;
  }
 catch (  CloneNotSupportedException e) {
    return null;
  }
}
