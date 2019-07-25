@Override public BigDecimal magnitude(Object value){
  OptionalInt narrowed=narrow(value);
  return narrowed.isPresent() ? BigDecimal.valueOf(narrowed.getAsInt()) : ZERO;
}
