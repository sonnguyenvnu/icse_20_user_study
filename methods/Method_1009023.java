/** 
 * Method translate translates a message ID into an internationalized String, see also <CODE>XMLSecurityException.getExceptionMessage()</CODE>
 * @param message
 * @return message translated
 */
public static String translate(String message){
  return getExceptionMessage(message);
}
