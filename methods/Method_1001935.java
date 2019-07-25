public static String unhide(String s){
  final StringBuilder result=new StringBuilder();
  for (int i=0; i < s.length(); i++) {
    final char c=s.charAt(i);
    result.append(unhideChar(c));
  }
  return result.toString();
}
