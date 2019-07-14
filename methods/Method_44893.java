public BigDecimal calcAsk(){
  return ask.add(BASIS_POINT_MULTIPLIER.multiply(askBP));
}
