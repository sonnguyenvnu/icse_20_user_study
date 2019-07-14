public static int checkFromToIndex(int fromIndex,int toIndex,int length){
  if (fromIndex < 0 || toIndex < fromIndex || length < toIndex) {
    throw new IndexOutOfBoundsException();
  }
  return fromIndex;
}
