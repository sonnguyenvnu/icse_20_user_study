@Override public String select(String text){
  Matcher matcher=regex.matcher(text);
  return matcher.replaceAll(replacement);
}
