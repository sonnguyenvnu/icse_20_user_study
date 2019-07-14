/** 
 * ??totp?????
 * @param accoName
 * @param domain
 * @param secretBase32
 * @return
 */
public static String generateTotpString(String accoName,String domain,String secretBase32){
  return "otpauth://totp/" + accoName + "@" + domain + "?secret=" + secretBase32;
}
