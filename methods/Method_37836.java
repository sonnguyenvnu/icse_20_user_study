public static boolean isWordChar(final char c){
  return isDigit(c) || isAlpha(c) || (c == '_');
}
