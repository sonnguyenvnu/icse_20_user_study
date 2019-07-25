public static String indent(int n,String ss){
  String s="";
  if (n >= blanks.length)   n=blanks.length - 1;
  for (int i=0; i < ss.length(); i++) {
    s+=ss.charAt(i);
    if (ss.charAt(i) == '\n' && i != ss.length() - 1) {
      s+=blanks[n];
    }
  }
  return s;
}
