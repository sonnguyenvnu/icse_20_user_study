/** 
 * Start a call to callback instances, dealing with common generic type concerns and exceptions.
 * @param callbackType the callback type (a {@link FunctionalInterface functionalinterface})
 * @param callbackInstances the callback instances (elements may be lambdas)
 * @param argument the primary argument passed to the callbacks
 * @param additionalArguments any additional arguments passed to the callbacks
 * @param < C > the callback type
 * @param < A > the primary argument type
 * @return a {@link Callbacks} instance that can be invoked.
 */
public static <C,A>Callbacks<C,A> callbacks(Class<C> callbackType,Collection<? extends C> callbackInstances,A argument,Object... additionalArguments){
  Assert.notNull(callbackType,"CallbackType must not be null");
  Assert.notNull(callbackInstances,"CallbackInstances must not be null");
  return new Callbacks<>(callbackType,callbackInstances,argument,additionalArguments);
}
