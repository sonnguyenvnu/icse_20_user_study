/** 
 * Adds a handler that delegates to the given handler if the  {@code request} {@code HTTPMethod} is {@code OPTIONS} and the {@code path} is at the current root.
 * @param handler the handler to delegate to
 * @return this
 * @since 1.1
 * @see Chain#get(Handler)
 * @see Chain#post(Handler)
 * @see Chain#put(Handler)
 * @see Chain#delete(Handler)
 */
default Chain options(Handler handler){
  return options("",handler);
}
