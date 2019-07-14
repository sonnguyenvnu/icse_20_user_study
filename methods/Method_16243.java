/** 
 * This method generates a TOTP value for the given set of parameters.
 * @param key:          the shared secret, HEX encoded
 * @param time:         a value that reflects a time
 * @param returnDigits: number of digits to return
 * @param crypto:       the crypto function to use
 * @return : a numeric String in base 10 that includes truncationDigits digits
 */
public static String generateTOTP(String key,String time,String returnDigits,String crypto){
  int codeDigits=Integer.decode(returnDigits);
  StringBuilder result;
  StringBuilder timeBuilder=new StringBuilder(time);
  while (timeBuilder.length() < 16)   timeBuilder.insert(0,"0");
  time=timeBuilder.toString();
  byte[] msg=hexStr2Bytes(time);
  byte[] k=hexStr2Bytes(key);
  byte[] hash=hmac_sha(crypto,k,msg);
  int offset=hash[hash.length - 1] & 0xf;
  int binary=((hash[offset] & 0x7f) << 24) | ((hash[offset + 1] & 0xff) << 16) | ((hash[offset + 2] & 0xff) << 8) | (hash[offset + 3] & 0xff);
  int otp=binary % DIGITS_POWER[codeDigits];
  result=new StringBuilder(Integer.toString(otp));
  while (result.length() < codeDigits) {
    result.insert(0,"0");
  }
  return result.toString();
}
