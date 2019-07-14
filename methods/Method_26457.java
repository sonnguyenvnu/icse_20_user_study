private static String upperCamelToken(String s){
  return "" + Ascii.toUpperCase(s.charAt(0)) + (s.length() == 1 ? "" : s.substring(1));
}
