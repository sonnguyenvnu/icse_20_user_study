public static int checkFromIndexSize(int fromIndex,int size,int length){
  if ((length | fromIndex | size) < 0 || length - fromIndex < size) {
    throw new IndexOutOfBoundsException();
  }
  return fromIndex;
}
