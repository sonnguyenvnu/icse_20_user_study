/** 
 * ??????? ".00"
 */
public static String formatMoney(double d){
  String tmp=formatCurrency(d);
  if (tmp.endsWith(".00")) {
    return tmp.substring(0,tmp.length() - 3);
  }
 else {
    return tmp;
  }
}
