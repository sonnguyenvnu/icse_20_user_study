@Override protected String _val(Object val){
  if (null == val)   return "null";
  if (val instanceof CharSequence) {
    if ("-obj-".equals(val))     return "{}";
    String s=Strings.trim(val.toString());
    if (Strings.isQuoteBy(s,'[',']'))     return s;
  }
  return Json.toJson(val,_fmt);
}
