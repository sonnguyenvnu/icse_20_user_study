public static BigDecimal formatBigDecimal(BigDecimal data,int scal){
  if (null == data)   return new BigDecimal(0.00);
  return data.setScale(scal,BigDecimal.ROUND_HALF_UP);
}
