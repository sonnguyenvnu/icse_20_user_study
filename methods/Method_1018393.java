public static String encode(long num,String symbols){
  final int B=symbols.length();
  StringBuilder sb=new StringBuilder();
  while (num != 0) {
    sb.append(symbols.charAt((int)(num % B)));
    num/=B;
  }
  return sb.reverse().toString();
}
