/** 
 * Creates a  {@code EnumerationMutationHandler} instance from the specified function pointer.
 * @return the new {@code EnumerationMutationHandler}
 */
public static EnumerationMutationHandler create(long functionPointer){
  EnumerationMutationHandlerI instance=Callback.get(functionPointer);
  return instance instanceof EnumerationMutationHandler ? (EnumerationMutationHandler)instance : new Container(functionPointer,instance);
}
