@Override public String _apply(final Element element){
  final StringBuilder strBuilder=new StringBuilder();
  for (  final String field : fields.keySet()) {
    final Object value=getFieldValue(element,field);
    if (null != value) {
      strBuilder.append(quoteString(value));
    }
    strBuilder.append(COMMA);
  }
  if (!constants.isEmpty()) {
    for (    final String constant : constants.keySet()) {
      strBuilder.append(quoteString(constant));
      strBuilder.append(COMMA);
    }
  }
  if (strBuilder.length() < 1) {
    return "";
  }
  return strBuilder.substring(0,strBuilder.length() - 1);
}
