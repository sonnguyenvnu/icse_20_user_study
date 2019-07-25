@Override public BigDecimal magnitude(Object value){
  Properties narrowed=narrow(value);
  if (narrowed.isEmpty())   return ZERO;
  BigDecimal keysMagnitude=narrowed.keySet().stream().map(e -> stringGenerator.magnitude(e)).reduce(ZERO,BigDecimal::add);
  BigDecimal valuesMagnitude=narrowed.values().stream().map(e -> stringGenerator.magnitude(e)).reduce(ZERO,BigDecimal::add);
  return BigDecimal.valueOf(narrowed.size()).multiply(keysMagnitude).add(valuesMagnitude);
}
