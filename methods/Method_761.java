public static boolean isIdent(char ch){
  return ch < identifierFlags.length && identifierFlags[ch];
}
