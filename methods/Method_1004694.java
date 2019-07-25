/** 
 * Start a call to a single callback instance, dealing with common generic type concerns and exceptions.
 * @param callbackType the callback type (a {@link FunctionalInterface functionalinterface})
 * @param callbackInstance the callback instance (may be a lambda)
 * @param argument the primary argument passed to the callback
 * @param additionalArguments any additional arguments passed to the callback
 * @param < C > the callback type
 * @param < A > the primary argument type
 * @return a {@link Callback} instance that can be invoked.
 */
public static <C,A>Callback<C,A> callback(Class<C> callbackType,C callbackInstance,A argument,Object... additionalArguments){
  Assert.notNull(callbackType,"CallbackType must not be null");
  Assert.notNull(callbackInstance,"CallbackInstance must not be null");
  return new Callback<>(callbackType,callbackInstance,argument,additionalArguments);
}
