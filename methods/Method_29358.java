/** 
 * ????
 * @param num
 * @return
 */
private double remainNumberTwoPoint(double num){
  DecimalFormat df=new DecimalFormat("0.00");
  return NumberUtils.toDouble(df.format(num));
}
