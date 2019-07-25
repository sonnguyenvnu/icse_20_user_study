@Override public <C>SpanContext extract(C carrier,Getter<C> getter) throws SpanContextParseException {
  checkNotNull(carrier,"carrier");
  checkNotNull(getter,"getter");
  TraceId traceId;
  SpanId spanId;
  TraceOptions traceOptions;
  String traceparent=getter.get(carrier,TRACEPARENT);
  if (traceparent == null) {
    throw new SpanContextParseException("Traceparent not present");
  }
  try {
    checkArgument(traceparent.charAt(TRACE_OPTION_OFFSET - 1) == TRACEPARENT_DELIMITER && (traceparent.length() == TRACEPARENT_HEADER_SIZE || (traceparent.length() > TRACEPARENT_HEADER_SIZE && traceparent.charAt(TRACEPARENT_HEADER_SIZE) == TRACEPARENT_DELIMITER)) && traceparent.charAt(SPAN_ID_OFFSET - 1) == TRACEPARENT_DELIMITER && traceparent.charAt(TRACE_OPTION_OFFSET - 1) == TRACEPARENT_DELIMITER,"Missing or malformed TRACEPARENT.");
    traceId=TraceId.fromLowerBase16(traceparent,TRACE_ID_OFFSET);
    spanId=SpanId.fromLowerBase16(traceparent,SPAN_ID_OFFSET);
    traceOptions=TraceOptions.fromLowerBase16(traceparent,TRACE_OPTION_OFFSET);
  }
 catch (  IllegalArgumentException e) {
    throw new SpanContextParseException("Invalid traceparent: " + traceparent,e);
  }
  String tracestate=getter.get(carrier,TRACESTATE);
  try {
    if (tracestate == null || tracestate.isEmpty()) {
      return SpanContext.create(traceId,spanId,traceOptions,TRACESTATE_DEFAULT);
    }
    Tracestate.Builder tracestateBuilder=Tracestate.builder();
    List<String> listMembers=TRACESTATE_ENTRY_DELIMITER_SPLITTER.splitToList(tracestate);
    checkArgument(listMembers.size() <= TRACESTATE_MAX_MEMBERS,"Tracestate has too many elements.");
    for (int i=listMembers.size() - 1; i >= 0; i--) {
      String listMember=listMembers.get(i);
      int index=listMember.indexOf(TRACESTATE_KEY_VALUE_DELIMITER);
      checkArgument(index != -1,"Invalid tracestate list-member format.");
      tracestateBuilder.set(listMember.substring(0,index),listMember.substring(index + 1,listMember.length()));
    }
    return SpanContext.create(traceId,spanId,traceOptions,tracestateBuilder.build());
  }
 catch (  IllegalArgumentException e) {
    throw new SpanContextParseException("Invalid tracestate: " + tracestate,e);
  }
}
