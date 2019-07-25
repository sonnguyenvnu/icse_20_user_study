private String printable(char ch){
  if (ch >= 32 && ch <= 126) {
    return Character.toString(ch);
  }
  return " (0x" + Integer.toHexString(ch) + ") ";
}
