private static StringBuilder reverse(final StringBuilder s){
  final StringBuilder sb=new StringBuilder(s.length());
  for (int i=s.length() - 1; i >= 0; i--) {
    sb.append(s.charAt(i));
  }
  return sb;
}
