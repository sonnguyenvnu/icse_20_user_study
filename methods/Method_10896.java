/** 
 * ??????
 * @param cardNo
 * @return
 */
public static boolean isBankCard(String cardNo){
  Pattern p=Pattern.compile("^\\d{16,19}$|^\\d{6}[- ]\\d{10,13}$|^\\d{4}[- ]\\d{4}[- ]\\d{4}[- ]\\d{4,7}$");
  Matcher m=p.matcher(cardNo);
  return m.matches();
}
