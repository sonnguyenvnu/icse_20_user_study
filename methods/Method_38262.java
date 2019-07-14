protected static StringBuilder toLowercase(final StringBuilder string){
  final int strLen=string.length();
  for (int i=0; i < strLen; i++) {
    char c=string.charAt(i);
    char lowercaseChar=Character.toLowerCase(c);
    if (c != lowercaseChar) {
      string.setCharAt(i,lowercaseChar);
    }
  }
  return string;
}
