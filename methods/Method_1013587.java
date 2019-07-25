/** 
 * Unconditioned way to throw an exception, the runtime will chain the cause
 * @param errorCode error code of explanation
 * @param cause reason of the fail and the message for the runtime exception
 */
public static void fail(int errorCode,Throwable cause){
  throw new TLCRuntimeException(errorCode,MP.getMessage(errorCode,cause.getMessage()),cause);
}
