/** 
 * 15??18???????????? ?????
 * @param idCard
 * @return
 */
public static boolean validateIdCard(String idCard){
  String regIdCard="^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$";
  Pattern p=Pattern.compile(regIdCard);
  return p.matcher(idCard).matches();
}
