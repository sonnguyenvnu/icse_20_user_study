/** 
 * ??????4??? 130****0000
 * @param mobile_phone ????
 * @return 130****0000
 */
public static String hideMobilePhone4(String mobile_phone){
  if (mobile_phone.length() != 11) {
    return "???????";
  }
  return mobile_phone.substring(0,3) + "****" + mobile_phone.substring(7,11);
}
