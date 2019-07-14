private static boolean containsAtLeast(String str,char c,int min){
  if (min > str.length()) {
    return false;
  }
  int count=0;
  for (int i=0; i < str.length(); i++) {
    if (str.charAt(i) == c) {
      count++;
      if (count >= min) {
        return true;
      }
    }
  }
  return false;
}
