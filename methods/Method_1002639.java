/** 
 * Use this to ensure the trace context propagates to children. <p>Ex. <pre> {@code // Ensure the trace context propagates to children ctx.onChild(RequestContextCurrentTraceContext::copy);}</pre>
 */
public static void copy(RequestContext src,RequestContext dst){
  dst.attr(TRACE_CONTEXT_KEY).set(src.attr(TRACE_CONTEXT_KEY).get());
}
