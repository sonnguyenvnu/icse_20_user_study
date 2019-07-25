/** 
 * @param execution the execution that this segment belongs to
 * @param execType indicates whether this segment is execution on a compute or blocking thread
 * @param executionSegment the execution segment that is to be executed
 * @throws Exception any
 */
public void intercept(Execution execution,ExecType execType,Block executionSegment) throws Exception {
  MDCHolder holder=execution.maybeGet(MDCHolder.TYPE).orElse(null);
  if (holder == null) {
    MDC.clear();
    holder=new MDCHolder();
    init.execute(execution);
    execution.add(MDCHolder.TYPE,holder);
  }
 else {
    if (holder.map == null) {
      MDC.clear();
    }
 else {
      MDC.setContextMap(holder.map);
    }
  }
  try {
    executionSegment.execute();
  }
  finally {
    holder.map=MDC.getCopyOfContextMap();
    MDC.clear();
  }
}
