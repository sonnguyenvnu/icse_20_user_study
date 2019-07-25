@Override public String encode(CommonLogSpan commonLogSpan) throws IOException {
  if (commonLogSpan.getSofaTracerSpanContext() == null) {
    return StringUtils.EMPTY_STRING;
  }
  SofaTracerSpanContext spanContext=commonLogSpan.getSofaTracerSpanContext();
  XStringBuilder xsb=new XStringBuilder();
  xsb.append(Timestamp.format(commonLogSpan.getStartTime())).append(commonLogSpan.getTagsWithStr().get(SpanTags.CURR_APP_TAG.getKey())).append(spanContext.getTraceId()).append(spanContext.getSpanId());
  this.appendStr(xsb,commonLogSpan);
  return xsb.toString();
}
