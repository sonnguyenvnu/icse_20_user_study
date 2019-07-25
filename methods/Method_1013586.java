/** 
 * Unconditioned way to throw an exception
 * @param errorCode error code of explanation
 * @param parameter parameter for the message
 */
public static void fail(int errorCode,String parameter){
  throw new TLCRuntimeException(errorCode,new String[]{parameter},MP.getMessage(errorCode,parameter));
}
