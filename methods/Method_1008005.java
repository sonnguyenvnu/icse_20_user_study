@Override protected String process(String value){
  return pattern.matcher(value).replaceAll(replacement);
}
