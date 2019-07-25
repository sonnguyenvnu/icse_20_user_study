private List<String> extracted(final String text){
  final int x=text.indexOf(')');
  return Arrays.asList(StringUtils.trin(text.substring(x + 1)));
}
