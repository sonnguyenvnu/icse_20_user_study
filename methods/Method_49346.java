public static boolean isAsciiString(String input){
  Preconditions.checkNotNull(input);
  for (int i=0; i < input.length(); i++) {
    int c=input.charAt(i);
    if (c > 127 || c <= 0)     return false;
  }
  return true;
}
