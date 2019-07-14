private static boolean containsLowerCase(String text){
  for (int i=0; i < text.length(); i++) {
    char c=text.charAt(i);
    if (c >= 'a' && c <= 'z') {
      return true;
    }
  }
  return false;
}
