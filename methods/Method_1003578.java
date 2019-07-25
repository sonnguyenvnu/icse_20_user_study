protected String unquote(String s){
  if (s == null) {
    return null;
  }
  return isQuotedString(s) ? s.substring(1,s.length() - 1) : s;
}
