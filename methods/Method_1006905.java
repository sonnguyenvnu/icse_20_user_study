/** 
 * Register a context with the current thread - always put a matching  {@link #close()} call in a finally block toensure that the correct context is available in the enclosing block.
 * @param execution the execution to register
 * @param propertyContext instance of {@link BatchPropertyContext} to be registered with this thread.
 * @return a new context or the current one if it has the sameexecution
 */
@Nullable public C register(@Nullable E execution,@Nullable BatchPropertyContext propertyContext){
  if (execution == null) {
    return null;
  }
  getCurrent().push(execution);
  C context;
synchronized (contexts) {
    context=contexts.get(execution);
    if (context == null) {
      context=createNewContext(execution,propertyContext);
      contexts.put(execution,context);
    }
  }
  increment();
  return context;
}
