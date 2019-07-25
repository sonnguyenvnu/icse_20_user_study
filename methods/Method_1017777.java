@Override protected Function<Float,BigDecimal> widen(){
  return BigDecimal::valueOf;
}
