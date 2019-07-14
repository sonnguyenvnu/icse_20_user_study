/** 
 * ?????? ?? ?"-" ?"+86" ??phone
 * @param phone
 * @return
 */
public static String getCorrectPhone(String phone){
  if (isNotEmpty(phone,true) == false) {
    return "";
  }
  phone=getNoBlankString(phone);
  phone=phone.replaceAll("-","");
  if (phone.startsWith("+86")) {
    phone=phone.substring(3);
  }
  return phone;
}
