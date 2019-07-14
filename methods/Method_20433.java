static String removeSetPrefix(String string){
  if (!PATTERN_STARTS_WITH_SET.matcher(string).matches()) {
    return string;
  }
  return String.valueOf(string.charAt(3)).toLowerCase() + string.substring(4);
}
