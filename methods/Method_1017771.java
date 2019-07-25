@Override protected Function<BigDecimal,Double> narrow(){
  return BigDecimal::doubleValue;
}
