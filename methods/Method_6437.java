private static boolean inArray(char c,char[] a){
  for (  char cc : a) {
    if (cc == c) {
      return true;
    }
  }
  return false;
}
