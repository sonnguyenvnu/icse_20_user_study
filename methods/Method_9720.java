@Override public String toString(){
  return CATEGORY_ELEMENT.replace("${term}",StringEscapeUtils.escapeXml(term));
}
