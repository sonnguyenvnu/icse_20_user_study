/** 
 * Method translate translates a message ID into an internationalized String, see alse <CODE>XMLSecurityException.getExceptionMEssage()</CODE>. The strings are stored in the <CODE>ResourceBundle</CODE>, which is identified in <CODE>exceptionMessagesResourceBundleBase</CODE>
 * @param message
 * @param args is an <CODE>Object[]</CODE> array of strings which are inserted into the String which is retrieved from the <CODE>ResouceBundle</CODE>
 * @return message translated
 */
public static String translate(String message,Object[] args){
  return getExceptionMessage(message,args);
}
