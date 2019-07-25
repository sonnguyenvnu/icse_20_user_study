@Override public BigDecimal magnitude(Object value){
  OptionalLong narrowed=narrow(value);
  return narrowed.isPresent() ? BigDecimal.valueOf(narrowed.getAsLong()) : ZERO;
}
