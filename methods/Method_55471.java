public static int checkIndex(int index,int length){
  if (index < 0 || length <= index) {
    throw new IndexOutOfBoundsException();
  }
  return index;
}
