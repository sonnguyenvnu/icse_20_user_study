/** 
 * Unconditioned way to throw an exception
 * @param errorCode error code of explanation
 */
public static void fail(int errorCode){
  throw new TLCRuntimeException(errorCode,MP.getMessage(errorCode));
}
