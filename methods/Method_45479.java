private static boolean startsWithExcludePrefix(String excludeName){
  char c=excludeName.charAt(0);
  return c == '-' || c == '!';
}
