/** 
 * Resolves method name of method reference. Argument is used so  {@link #get()}can be called in convenient way. For methods that returns string, value will be returned immediately.
 */
public String ref(final Object dummy){
  if (dummy != null) {
    if (dummy instanceof String) {
      return (String)dummy;
    }
    throw new MethrefException("Target method not collected");
  }
  return ref();
}
