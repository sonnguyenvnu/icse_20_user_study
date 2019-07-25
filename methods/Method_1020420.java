static void close(SpanFunction spanFunction){
  SpanContext current=CURRENT_SPAN.get();
  while (current != null) {
    spanFunction.apply(current.span);
    current=removeCurrentSpanInternal(current.parent);
    if (current == null || !current.autoClose) {
      return;
    }
  }
}
