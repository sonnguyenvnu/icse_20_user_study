private static String convertToAsciiNumber(String convId){
  StringBuilder buf=new StringBuilder(convId);
  for (int i=0; i < buf.length(); i++) {
    char ch=buf.charAt(i);
    int digit=Character.digit(ch,10);
    if (digit >= 0) {
      buf.setCharAt(i,(char)('0' + digit));
    }
  }
  return buf.toString();
}
