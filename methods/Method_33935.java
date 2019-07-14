private static String strip(String string,char c){
  return trimLeadingCharacter(trimTrailingCharacter(string.trim(),c),c);
}
