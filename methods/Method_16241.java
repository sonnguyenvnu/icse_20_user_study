/** 
 * This method generates a TOTP value for the given set of parameters.
 * @param key:          the shared secret, HEX encoded
 * @param time:         a value that reflects a time
 * @param returnDigits: number of digits to return
 * @return : a numeric String in base 10 that includes truncationDigits digits
 */
public static String generateTOTP256(String key,String time,String returnDigits){
  return generateTOTP(key,time,returnDigits,"HmacSHA256");
}
