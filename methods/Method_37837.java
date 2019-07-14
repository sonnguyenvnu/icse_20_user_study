public static boolean isPropertyNameChar(final char c){
  return isDigit(c) || isAlpha(c) || (c == '_') || (c == '.') || (c == '[') || (c == ']');
}
