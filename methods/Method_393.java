public static boolean isValidArray(String str){
  if (str == null || str.length() == 0) {
    return false;
  }
  JSONScanner lexer=new JSONScanner(str);
  try {
    lexer.nextToken();
    final int token=lexer.token();
    if (token == JSONToken.LBRACKET) {
      lexer.skipArray(true);
      return lexer.token() == JSONToken.EOF;
    }
    return false;
  }
 catch (  Exception ex) {
    return false;
  }
 finally {
    lexer.close();
  }
}
