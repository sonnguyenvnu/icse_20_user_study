public static String unicode(String s){
  final StringBuilder result=new StringBuilder();
  for (  char c : s.toCharArray()) {
    if (c > 127 || c == '&' || c == '|') {
      final int i=c;
      result.append("&#" + i + ";");
    }
 else {
      result.append(c);
    }
  }
  return result.toString();
}
