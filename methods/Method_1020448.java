@Override public <C>void inject(SpanContext spanContext,C carrier,Setter<C> setter){
  checkNotNull(spanContext,"spanContext");
  checkNotNull(setter,"setter");
  checkNotNull(carrier,"carrier");
  setter.put(carrier,X_B3_TRACE_ID,spanContext.getTraceId().toLowerBase16());
  setter.put(carrier,X_B3_SPAN_ID,spanContext.getSpanId().toLowerBase16());
  if (spanContext.getTraceOptions().isSampled()) {
    setter.put(carrier,X_B3_SAMPLED,SAMPLED_VALUE);
  }
}
