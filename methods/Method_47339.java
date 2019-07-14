private static int getLinkPosition(String[] array){
  for (int i=0; i < array.length; i++) {
    if (array[i].contains("->"))     return i;
  }
  return 0;
}
