@Override public boolean contains(short x){
  int index=bufferedUnsignedInterleavedBinarySearch(valueslength,0,nbrruns,x);
  if (index >= 0) {
    return true;
  }
  index=-index - 2;
  if (index != -1) {
    int offset=toIntUnsigned(x) - toIntUnsigned(getValue(index));
    int le=toIntUnsigned(getLength(index));
    if (offset <= le) {
      return true;
    }
  }
  return false;
}
