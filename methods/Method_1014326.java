/** 
 * Rounds a double value.
 */
protected BigDecimal round(Double number){
  BigDecimal bd=new BigDecimal(number == null ? "0" : number.toString());
  String stringBd=bd.toPlainString();
  int scale=stringBd.length() - (stringBd.lastIndexOf('.') + 1);
  return bd.setScale(scale > 2 ? 6 : 2,RoundingMode.HALF_UP);
}
