/** 
 * Push a span into the thread context, with the option to have it auto close if any child spans are themselves closed. Use autoClose=true if you start a new span with a parent that wasn't already in thread context.
 */
static void push(Span span,boolean autoClose){
  if (isCurrent(span)) {
    return;
  }
  setSpanContextInternal(new SpanContext(span,autoClose));
}
