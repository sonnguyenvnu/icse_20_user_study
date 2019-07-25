@Override public BigDecimal magnitude(Object value){
  OptionalDouble narrowed=narrow(value);
  return narrowed.isPresent() ? doubleGenerator.magnitude(narrowed.getAsDouble()) : ZERO;
}
