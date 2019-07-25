@Override public MappeableContainer add(short k){
  int index=bufferedUnsignedInterleavedBinarySearch(valueslength,0,nbrruns,k);
  if (index >= 0) {
    return this;
  }
  index=-index - 2;
  if (index >= 0) {
    int offset=toIntUnsigned(k) - toIntUnsigned(getValue(index));
    int le=toIntUnsigned(getLength(index));
    if (offset <= le) {
      return this;
    }
    if (offset == le + 1) {
      if (index + 1 < nbrruns) {
        if (toIntUnsigned(getValue(index + 1)) == toIntUnsigned(k) + 1) {
          setLength(index,(short)(getValue(index + 1) + getLength(index + 1) - getValue(index)));
          recoverRoomAtIndex(index + 1);
          return this;
        }
      }
      incrementLength(index);
      return this;
    }
    if (index + 1 < nbrruns) {
      if (toIntUnsigned(getValue(index + 1)) == toIntUnsigned(k) + 1) {
        setValue(index + 1,k);
        setLength(index + 1,(short)(getLength(index + 1) + 1));
        return this;
      }
    }
  }
  if (index == -1) {
    if (0 < nbrruns) {
      if (getValue(0) == k + 1) {
        incrementLength(0);
        decrementValue(0);
        return this;
      }
    }
  }
  makeRoomAtIndex(index + 1);
  setValue(index + 1,k);
  setLength(index + 1,(short)0);
  return this;
}
