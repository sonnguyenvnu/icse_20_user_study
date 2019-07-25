@Override public SofaTracerSpanContext extract(TextMap carrier){
  if (carrier == null) {
    return SofaTracerSpanContext.rootStart();
  }
  String traceId=null;
  String spanId=null;
  String parentId=null;
  boolean sampled=false;
  boolean isGetSampled=false;
  Map<String,String> sysBaggage=new ConcurrentHashMap<String,String>();
  Map<String,String> bizBaggage=new ConcurrentHashMap<String,String>();
  for (  Map.Entry<String,String> entry : carrier) {
    String key=entry.getKey();
    if (StringUtils.isBlank(key)) {
      continue;
    }
    if (traceId == null && TRACE_ID_KEY_HEAD.equalsIgnoreCase(key)) {
      traceId=decodedValue(entry.getValue());
    }
    if (spanId == null && SPAN_ID_KEY_HEAD.equalsIgnoreCase(key)) {
      spanId=decodedValue(entry.getValue());
    }
    if (parentId == null && PARENT_SPAN_ID_KEY_HEAD.equalsIgnoreCase(key)) {
      parentId=decodedValue(entry.getValue());
    }
    if (!isGetSampled && SAMPLED_KEY_HEAD.equalsIgnoreCase(key)) {
      String valueTmp=decodedValue(entry.getValue());
      if ("1".equals(valueTmp)) {
        sampled=true;
      }
 else       if ("0".equals(valueTmp)) {
        sampled=false;
      }
 else {
        sampled=Boolean.parseBoolean(valueTmp);
      }
      isGetSampled=true;
    }
    if (key.indexOf(BAGGAGE_SYS_KEY_PREFIX) == 0) {
      String keyTmp=StringUtils.unescapeEqualAndPercent(key).substring(BAGGAGE_SYS_KEY_PREFIX.length());
      String valueTmp=StringUtils.unescapeEqualAndPercent(decodedValue(entry.getValue()));
      sysBaggage.put(keyTmp,valueTmp);
    }
    if (key.indexOf(BAGGAGE_KEY_PREFIX) == 0) {
      String keyTmp=StringUtils.unescapeEqualAndPercent(key).substring(BAGGAGE_KEY_PREFIX.length());
      String valueTmp=StringUtils.unescapeEqualAndPercent(decodedValue(entry.getValue()));
      bizBaggage.put(keyTmp,valueTmp);
    }
  }
  if (traceId == null) {
    return SofaTracerSpanContext.rootStart();
  }
  if (spanId == null) {
    spanId=SofaTracer.ROOT_SPAN_ID;
  }
  if (parentId == null) {
    parentId=StringUtils.EMPTY_STRING;
  }
  SofaTracerSpanContext sofaTracerSpanContext=new SofaTracerSpanContext(traceId,spanId,parentId,sampled);
  if (sysBaggage.size() > 0) {
    sofaTracerSpanContext.addSysBaggage(sysBaggage);
  }
  if (bizBaggage.size() > 0) {
    sofaTracerSpanContext.addBizBaggage(bizBaggage);
  }
  return sofaTracerSpanContext;
}
