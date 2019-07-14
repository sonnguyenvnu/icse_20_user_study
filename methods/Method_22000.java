private String truncateString(final String str){
  return !Strings.isNullOrEmpty(str) && str.length() > 4000 ? str.substring(0,4000) : str;
}
