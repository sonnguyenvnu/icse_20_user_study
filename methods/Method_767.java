public static boolean isValidJsonpQueryParam(String value){
  if (value == null || value.length() == 0) {
    return false;
  }
  for (int i=0, len=value.length(); i < len; ++i) {
    char ch=value.charAt(i);
    if (ch != '.' && !IOUtils.isIdent(ch)) {
      return false;
    }
  }
  return true;
}
