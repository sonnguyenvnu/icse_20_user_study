@Override @javax.annotation.Nullable public Span close(Span span){
  if (span == null) {
    return null;
  }
  final Span savedSpan=span.getSavedSpan();
  Span current=OpenCensusSleuthSpanContextHolder.getCurrentSpan();
  if (current == null || !span.equals(current)) {
    ExceptionUtils.warn("Tried to close span but it is not the current span: " + span + ".  You may have forgotten to close or detach " + current);
  }
 else {
    span.stop();
    if (savedSpan != null && span.getParents().contains(savedSpan.getSpanId())) {
      this.spanReporter.report(span);
      this.spanLogger.logStoppedSpan(savedSpan,span);
    }
 else {
      if (!span.isRemote()) {
        this.spanReporter.report(span);
        this.spanLogger.logStoppedSpan(null,span);
      }
    }
    OpenCensusSleuthSpanContextHolder.close(new OpenCensusSleuthSpanContextHolder.SpanFunction(){
      @Override public void apply(      Span closedSpan){
        OpenCensusSleuthTracer.this.spanLogger.logStoppedSpan(savedSpan,closedSpan);
      }
    }
);
  }
  return savedSpan;
}
