@Override public String encode(SofaTracerSpan sofaTracerSpan) throws IOException {
  JsonStringBuilder data=new JsonStringBuilder();
  data.appendBegin(CommonSpanTags.TIME,Timestamp.format(sofaTracerSpan.getEndTime()));
  Map<String,String> tagStr=sofaTracerSpan.getTagsWithStr();
  Map<String,Number> tagNum=sofaTracerSpan.getTagsWithNumber();
  SofaTracerSpanContext context=sofaTracerSpan.getSofaTracerSpanContext();
  data.append(CommonSpanTags.TRACE_ID,context.getTraceId());
  data.append(CommonSpanTags.SPAN_ID,context.getSpanId());
  data.append(Tags.SPAN_KIND.getKey(),tagStr.get(Tags.SPAN_KIND.getKey()));
  data.append(CommonSpanTags.LOCAL_APP,tagStr.get(CommonSpanTags.LOCAL_APP));
  data.append(CommonSpanTags.PROTOCOL,tagStr.get(CommonSpanTags.PROTOCOL));
  data.append(CommonSpanTags.SERVICE,tagStr.get(CommonSpanTags.SERVICE));
  data.append(CommonSpanTags.METHOD,tagStr.get(CommonSpanTags.METHOD));
  data.append(CommonSpanTags.INVOKE_TYPE,tagStr.get(CommonSpanTags.INVOKE_TYPE));
  data.append(CommonSpanTags.REMOTE_HOST,tagStr.get(CommonSpanTags.REMOTE_HOST));
  data.append(CommonSpanTags.REMOTE_PORT,tagStr.get(CommonSpanTags.REMOTE_PORT));
  data.append(CommonSpanTags.LOCAL_HOST,tagStr.get(CommonSpanTags.LOCAL_HOST));
  data.append(CommonSpanTags.CLIENT_SERIALIZE_TIME,tagNum.get(CommonSpanTags.CLIENT_SERIALIZE_TIME));
  data.append(CommonSpanTags.CLIENT_DESERIALIZE_TIME,tagNum.get(CommonSpanTags.CLIENT_DESERIALIZE_TIME));
  Number reqSizeNum=tagNum.get(CommonSpanTags.REQ_SIZE);
  data.append(CommonSpanTags.REQ_SIZE,reqSizeNum == null ? 0 : reqSizeNum.longValue());
  Number respSizeNum=tagNum.get(CommonSpanTags.REQ_SIZE);
  data.append(CommonSpanTags.RESP_SIZE,respSizeNum == null ? 0 : respSizeNum.longValue());
  data.append(CommonSpanTags.RESULT_CODE,tagStr.get(CommonSpanTags.RESULT_CODE));
  if (StringUtils.isNotBlank(tagStr.get(Tags.ERROR.getKey()))) {
    data.append(Tags.ERROR.getKey(),tagStr.get(Tags.ERROR.getKey()));
  }
  data.append(CommonSpanTags.CURRENT_THREAD_NAME,tagStr.get(CommonSpanTags.CURRENT_THREAD_NAME));
  data.append(CommonSpanTags.TIME_COST_MILLISECONDS,(sofaTracerSpan.getEndTime() - sofaTracerSpan.getStartTime()));
  this.appendBaggage(data,context);
  return data.toString();
}
