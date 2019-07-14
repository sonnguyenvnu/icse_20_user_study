private static String strip(String token,String prefix){
  return !prefix.isEmpty() && prefix.length() < token.length() && token.substring(0,prefix.length()).equalsIgnoreCase(prefix) ? token.substring(prefix.length()) : token;
}
