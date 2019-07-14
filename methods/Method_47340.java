private static int getColonPosition(String[] array){
  for (int i=0; i < array.length; i++) {
    if (array[i].contains(":"))     return i;
  }
  return -1;
}
