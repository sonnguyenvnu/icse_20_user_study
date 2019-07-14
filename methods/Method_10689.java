/** 
 * ?????? ? 3749 **** **** 330
 * @param cardNo ???
 * @return 3749 **** **** 330
 */
public static String formatCard(String cardNo){
  if (cardNo.length() < 8) {
    return "??????";
  }
  String card="";
  card=cardNo.substring(0,4) + " **** **** ";
  card+=cardNo.substring(cardNo.length() - 4);
  return card;
}
