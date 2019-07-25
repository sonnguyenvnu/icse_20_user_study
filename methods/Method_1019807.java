@Override public String encode(SofaTracerSpan span) throws IOException {
  JsonStringBuilder jsonStringBuilder=new JsonStringBuilder();
  jsonStringBuilder.appendBegin(CommonSpanTags.TIME,Timestamp.format(span.getEndTime()));
  appendSlot(jsonStringBuilder,span);
  return jsonStringBuilder.toString();
}
