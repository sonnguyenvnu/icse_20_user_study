public static String hide(String s){
  final StringBuilder result=new StringBuilder();
  for (int i=0; i < s.length(); i++) {
    final char c=s.charAt(i);
    if (c == '~' && i + 1 < s.length()) {
      i++;
      final char c2=s.charAt(i);
      if (isToBeHidden(c2)) {
        result.append(hideChar(c2));
      }
 else {
        result.append(c);
        result.append(c2);
      }
    }
 else {
      result.append(c);
    }
  }
  return result.toString();
}
