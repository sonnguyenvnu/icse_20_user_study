public static long decode(String s,String symbols){
  final int B=symbols.length();
  long num=0;
  for (  char ch : s.toCharArray()) {
    num*=B;
    num+=symbols.indexOf(ch);
  }
  return num;
}
