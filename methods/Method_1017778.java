@Override protected Function<BigDecimal,Float> narrow(){
  return BigDecimal::floatValue;
}
