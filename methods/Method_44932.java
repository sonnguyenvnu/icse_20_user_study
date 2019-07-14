private static BigDecimal withScale(BigDecimal value,int priceScale){
  try {
    return value.setScale(priceScale,RoundingMode.UNNECESSARY);
  }
 catch (  ArithmeticException e) {
    log.debug("Could not round {} to {} decimal places: {}",value,priceScale,e.getMessage());
    return value.setScale(priceScale,RoundingMode.CEILING);
  }
}
