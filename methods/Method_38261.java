protected static StringBuilder toUppercase(final StringBuilder string){
  final int strLen=string.length();
  for (int i=0; i < strLen; i++) {
    char c=string.charAt(i);
    char uppercaseChar=Character.toUpperCase(c);
    if (c != uppercaseChar) {
      string.setCharAt(i,uppercaseChar);
    }
  }
  return string;
}
