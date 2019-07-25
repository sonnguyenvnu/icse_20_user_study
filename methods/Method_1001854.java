private static int lines(String text){
  int result=0;
  for (int i=0; i < text.length(); i++) {
    if (text.charAt(i) == BackSlash.CHAR_NEWLINE) {
      result++;
    }
  }
  return result;
}
