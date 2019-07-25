/** 
 * ?????????
 * @param v1 ???
 * @param v2 ??
 * @return ??????
 */
public static double sub(double v1,double v2){
  BigDecimal b1=new BigDecimal(Double.toString(v1));
  BigDecimal b2=new BigDecimal(Double.toString(v2));
  return b1.subtract(b2).doubleValue();
}
