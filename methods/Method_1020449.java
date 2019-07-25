@Override public <C>SpanContext extract(C carrier,Getter<C> getter) throws SpanContextParseException {
  checkNotNull(carrier,"carrier");
  checkNotNull(getter,"getter");
  try {
    TraceId traceId;
    String traceIdStr=getter.get(carrier,X_B3_TRACE_ID);
    if (traceIdStr != null) {
      if (traceIdStr.length() == TraceId.SIZE) {
        traceIdStr=UPPER_TRACE_ID + traceIdStr;
      }
      traceId=TraceId.fromLowerBase16(traceIdStr);
    }
 else {
      throw new SpanContextParseException("Missing X_B3_TRACE_ID.");
    }
    SpanId spanId;
    String spanIdStr=getter.get(carrier,X_B3_SPAN_ID);
    if (spanIdStr != null) {
      spanId=SpanId.fromLowerBase16(spanIdStr);
    }
 else {
      throw new SpanContextParseException("Missing X_B3_SPAN_ID.");
    }
    TraceOptions traceOptions=TraceOptions.DEFAULT;
    if (SAMPLED_VALUE.equals(getter.get(carrier,X_B3_SAMPLED)) || FLAGS_VALUE.equals(getter.get(carrier,X_B3_FLAGS))) {
      traceOptions=TraceOptions.builder().setIsSampled(true).build();
    }
    return SpanContext.create(traceId,spanId,traceOptions,TRACESTATE_DEFAULT);
  }
 catch (  IllegalArgumentException e) {
    throw new SpanContextParseException("Invalid input.",e);
  }
}
