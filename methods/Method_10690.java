/** 
 * ??????
 * @param cardNo
 * @return
 */
public static String formatCardEnd4(String cardNo){
  if (cardNo.length() < 8) {
    return "??????";
  }
  String card="";
  card+=cardNo.substring(cardNo.length() - 4);
  return card;
}
