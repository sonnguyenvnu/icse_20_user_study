private static boolean contains(char[] array,char value){
  if (array == null || array.length == 0)   return false;
  for (  char aChar : array) {
    if (aChar == value)     return true;
  }
  return false;
}
