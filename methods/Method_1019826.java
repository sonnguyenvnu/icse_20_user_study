@Override public void inject(SofaTracerSpanContext spanContext,TextMap carrier){
  if (carrier == null || spanContext == null) {
    return;
  }
  carrier.put(TRACE_ID_KEY_HEAD,encodedValue(spanContext.getTraceId()));
  carrier.put(SPAN_ID_KEY_HEAD,encodedValue(spanContext.getSpanId()));
  carrier.put(PARENT_SPAN_ID_KEY_HEAD,encodedValue(spanContext.getParentId()));
  carrier.put(SPAN_ID_KEY_HEAD,encodedValue(spanContext.getSpanId()));
  carrier.put(SAMPLED_KEY_HEAD,encodedValue(String.valueOf(spanContext.isSampled())));
  for (  Map.Entry<String,String> entry : spanContext.getSysBaggage().entrySet()) {
    String key=BAGGAGE_SYS_KEY_PREFIX + StringUtils.escapePercentEqualAnd(entry.getKey());
    String value=encodedValue(StringUtils.escapePercentEqualAnd(entry.getValue()));
    carrier.put(key,value);
  }
  for (  Map.Entry<String,String> entry : spanContext.getBizBaggage().entrySet()) {
    String key=BAGGAGE_KEY_PREFIX + StringUtils.escapePercentEqualAnd(entry.getKey());
    String value=encodedValue(StringUtils.escapePercentEqualAnd(entry.getValue()));
    carrier.put(key,value);
  }
}
