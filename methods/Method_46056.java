/** 
 * ?double???????????????????
 * @param num1 ??1
 * @param num2 ??2
 * @return ????????
 */
public static int multiply(double num1,double num2){
  BigDecimal num1Bd=new BigDecimal(Double.toString(num1));
  BigDecimal num2Bd=new BigDecimal(Double.toString(num2));
  MathContext mathContext=new MathContext(num1Bd.precision(),RoundingMode.HALF_UP);
  return num1Bd.multiply(num2Bd,mathContext).intValue();
}
