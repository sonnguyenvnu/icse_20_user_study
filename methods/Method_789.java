public static boolean isNumber(String str){
  for (int i=0; i < str.length(); ++i) {
    char ch=str.charAt(i);
    if (ch == '+' || ch == '-') {
      if (i != 0) {
        return false;
      }
    }
 else     if (ch < '0' || ch > '9') {
      return false;
    }
  }
  return true;
}
