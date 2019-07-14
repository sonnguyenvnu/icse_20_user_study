/** 
 * Call this at the beginning of each request (from parent thread) to initialize the underlying context so that  {@link HystrixRequestVariableDefault} can be used on any children threads and be accessible fromthe parent thread. <p> <b>NOTE: If this method is called then <code>shutdown()</code> must also be called or a memory leak will occur.</b> <p> See class header JavaDoc for example Servlet Filter implementation that initializes and shuts down the context.
 */
public static HystrixRequestContext initializeContext(){
  HystrixRequestContext state=new HystrixRequestContext();
  requestVariables.set(state);
  return state;
}
