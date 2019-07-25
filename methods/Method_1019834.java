@Override public void finish(long endTime){
  this.setEndTime(endTime);
  this.sofaTracer.reportSpan(this);
  SpanExtensionFactory.logStoppedSpan(this);
}
