@Override protected boolean test(Object o){
  if (o instanceof Number) {
    return true;
  }
  String s=(o instanceof String) ? (String)o : o.toString();
  return StringUtils.isNumeric(s);
}
