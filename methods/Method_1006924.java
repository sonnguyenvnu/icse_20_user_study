/** 
 * Invoke the delegate method and return the result.
 * @see ItemProcessor#process(Object)
 */
@Override public O process(I item) throws Exception {
  return invokeDelegateMethodWithArgument(item);
}
