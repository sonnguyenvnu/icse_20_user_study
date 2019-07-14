public static long decode(String s,String symbols){
  final int B=symbols.length();
  long num=0;
  for (  char ch : s.toCharArray()) {
    num*=B;
    int pos=symbols.indexOf(ch);
    if (pos < 0)     throw new NumberFormatException("Symbol set does not match string");
    num+=pos;
  }
  return num;
}
