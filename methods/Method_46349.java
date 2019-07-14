public void appendSlot(JsonStringBuilder jsb,SofaTracerSpan span){
  SofaTracerSpanContext spanContext=span.getSofaTracerSpanContext();
  jsb.append(RpcSpanTags.TRACERID,spanContext.getTraceId());
  jsb.append(RpcSpanTags.SPANID,spanContext.getSpanId());
  Map<String,String> tagsWithStr=span.getTagsWithStr();
  if (CommonUtils.isNotEmpty(tagsWithStr)) {
    for (    Map.Entry<String,String> entry : tagsWithStr.entrySet()) {
      jsb.append(entry.getKey(),entry.getValue());
    }
  }
  Map<String,Number> tagsWithNumber=span.getTagsWithNumber();
  if (CommonUtils.isNotEmpty(tagsWithNumber)) {
    for (    Map.Entry<String,Number> entry : tagsWithNumber.entrySet()) {
      Number value=entry.getValue();
      jsb.append(entry.getKey(),value == null ? null : String.valueOf(value));
    }
  }
  Map<String,Boolean> tagsWithBool=span.getTagsWithBool();
  if (CommonUtils.isNotEmpty(tagsWithBool)) {
    for (    Map.Entry<String,Boolean> entry : tagsWithBool.entrySet()) {
      jsb.append(entry.getKey(),entry.getValue());
    }
  }
  jsb.append(RpcSpanTags.BAGGAGE,baggageSerialized(spanContext));
}
