@Override public SofaTracerSpanContext extract(TextMap carrier){
  if (carrier == null) {
    return SofaTracerSpanContext.rootStart();
  }
  SofaTracerSpanContext sofaTracerSpanContext=null;
  for (  Map.Entry<String,String> entry : carrier) {
    String key=entry.getKey();
    String value=entry.getValue();
    if (StringUtils.isBlank(key)) {
      continue;
    }
    if (FORMATER_KEY_HEAD.equalsIgnoreCase(key) && !StringUtils.isBlank(value)) {
      sofaTracerSpanContext=SofaTracerSpanContext.deserializeFromString(this.decodedValue(value));
    }
  }
  if (sofaTracerSpanContext == null) {
    return SofaTracerSpanContext.rootStart();
  }
  return sofaTracerSpanContext;
}
