/** 
 * Unconditioned way to throw an exception
 * @param errorCode error code of explanation
 * @param parameters parameters for the message
 */
public static void fail(int errorCode,String[] parameters){
  throw new TLCRuntimeException(errorCode,parameters,MP.getMessage(errorCode,parameters));
}
