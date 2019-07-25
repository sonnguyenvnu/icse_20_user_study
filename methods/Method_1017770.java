@Override protected Function<Double,BigDecimal> widen(){
  return BigDecimal::valueOf;
}
