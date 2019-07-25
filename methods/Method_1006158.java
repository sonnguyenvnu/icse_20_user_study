@Override public String format(String fieldText){
  return StringEscapeUtils.unescapeHtml4(fieldText).replaceAll("\\<[^>]*>","");
}
