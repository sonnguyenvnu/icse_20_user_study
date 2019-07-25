/** 
 * Checks whether the condition is true. Throws an unchecked exception if otherwise
 * @param condition condition the condition to check
 * @param errorCode error code of explanation
 * @throws RuntimeException
 */
public static void check(boolean condition,int errorCode) throws RuntimeException {
  if (!condition) {
    throw new TLCRuntimeException(MP.getMessage(errorCode));
  }
}
