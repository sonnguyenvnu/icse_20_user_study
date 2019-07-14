private static void loadSpace(){
  for (int i=Character.MIN_CODE_POINT; i <= Character.MAX_CODE_POINT; i++) {
    if (Character.isWhitespace(i) || Character.isSpaceChar(i)) {
      CONVERT[i]=' ';
    }
  }
}
