/** 
 * The following method added by LL on 5 Oct 2009 because, for some unknown reason,  javacc seems to have begun generating code that requires such a method. Someone removed it (probably Simon Z), presumably because it was no longer needed by javacc.  However, it was added again by LL on 7 April 2012 to replace check(boolean, int) above when it was called with error code EC.GENERAL, which produces no useful message. Checks whether the condition is true. Throws an unchecked exception if otherwise
 * @param condition condition the condition to check
 * @param errorMsg error explanation
 * @throws RuntimeException
 */
public static void check(boolean condition,String errorMsg) throws RuntimeException {
  if (!condition) {
    throw new TLCRuntimeException(errorMsg);
  }
}
