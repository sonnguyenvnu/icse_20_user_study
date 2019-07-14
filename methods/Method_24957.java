static private boolean isNegativeSign(int pos,String code){
  for (int i=pos; i >= 0; i--) {
    char c=code.charAt(i);
    if (c != ' ' && c != '\t') {
      return (c == ',' || c == '{' || c == '[' || c == '(' || c == '=' || c == '?' || c == '+' || c == '-' || c == '/' || c == '*' || c == '%' || c == '<' || c == '>' || c == ':' || c == '&' || c == '|' || c == '^' || c == '!' || c == '~');
    }
  }
  return false;
}
