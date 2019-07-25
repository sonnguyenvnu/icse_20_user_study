private static char hex(int b){
  final char ch=Character.forDigit(b & 0xF,16);
  if (Character.isLetter(ch)) {
    return Character.toUpperCase(ch);
  }
 else {
    return ch;
  }
}
