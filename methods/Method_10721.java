/** 
 * ???????100?
 * @param value ??
 * @param digit ?????
 * @return
 */
public static String getPercentValue(BigDecimal value,int digit){
  BigDecimal result=value.multiply(new BigDecimal(100));
  return getRoundUp(result,digit);
}
