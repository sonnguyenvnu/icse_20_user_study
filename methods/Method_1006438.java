@Override public MappeableContainer remove(short x){
  int index=bufferedUnsignedInterleavedBinarySearch(valueslength,0,nbrruns,x);
  if (index >= 0) {
    if (getLength(index) == 0) {
      recoverRoomAtIndex(index);
    }
 else {
      incrementValue(index);
      decrementLength(index);
    }
    return this;
  }
  index=-index - 2;
  if (index >= 0) {
    int offset=toIntUnsigned(x) - toIntUnsigned(getValue(index));
    int le=toIntUnsigned(getLength(index));
    if (offset < le) {
      this.setLength(index,(short)(offset - 1));
      int newvalue=toIntUnsigned(x) + 1;
      int newlength=le - offset - 1;
      makeRoomAtIndex(index + 1);
      this.setValue(index + 1,(short)newvalue);
      this.setLength(index + 1,(short)newlength);
      return this;
    }
 else     if (offset == le) {
      decrementLength(index);
    }
  }
  return this;
}
