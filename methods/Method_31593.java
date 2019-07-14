protected static boolean keywordIs(String expected,String actual){
  if (expected.length() != actual.length()) {
    return false;
  }
  for (int i=0; i < expected.length(); i++) {
    char ce=expected.charAt(i);
    char ca=actual.charAt(i);
    if (ce != ca && ce + ('a' - 'A') != ca) {
      return false;
    }
  }
  return true;
}
