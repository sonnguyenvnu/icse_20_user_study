private static <T>boolean arrayContains(T[] array,T value){
  for (int i=0, size=array.length; i < size; i++) {
    if (array[i] == value) {
      return true;
    }
  }
  return false;
}
