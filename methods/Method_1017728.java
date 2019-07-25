public BigDecimal magnitude(Object value){
  return generator.canShrink(value) ? generator.magnitude(value).abs() : ZERO;
}
