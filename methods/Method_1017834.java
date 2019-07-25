@Override public BigDecimal magnitude(Object value){
  Map<?,?> narrowed=narrow(value);
  if (narrowed.isEmpty())   return BigDecimal.ZERO;
  BigDecimal keysMagnitude=narrowed.keySet().stream().map(e -> componentGenerators().get(0).magnitude(e)).reduce(BigDecimal.ZERO,BigDecimal::add);
  BigDecimal valuesMagnitude=narrowed.values().stream().map(e -> componentGenerators().get(1).magnitude(e)).reduce(BigDecimal.ZERO,BigDecimal::add);
  return BigDecimal.valueOf(narrowed.size()).multiply(keysMagnitude).add(valuesMagnitude);
}
