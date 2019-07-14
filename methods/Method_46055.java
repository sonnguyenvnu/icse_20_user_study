/** 
 * ????????????
 * @param minuend   ???
 * @param reduction ??
 * @param scale     ?????????(????????)
 * @return ????
 */
public static double subtract(double minuend,double reduction,int scale){
  BigDecimal minuendBd=new BigDecimal(Double.toString(minuend));
  BigDecimal reductionBd=new BigDecimal(Double.toString(reduction));
  MathContext mathContext=new MathContext(scale,RoundingMode.HALF_UP);
  return minuendBd.subtract(reductionBd,mathContext).doubleValue();
}
