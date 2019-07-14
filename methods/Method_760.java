public static boolean firstIdentifier(char ch){
  return ch < IOUtils.firstIdentifierFlags.length && IOUtils.firstIdentifierFlags[ch];
}
