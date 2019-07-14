/** 
 * ??????Tracing??
 */
public void destroy(){
  if (Objects.nonNull(tracingContextThreadLocal.get())) {
    tracingContextThreadLocal.remove();
  }
}
