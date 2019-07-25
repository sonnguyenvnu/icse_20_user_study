@Override public BigDecimal magnitude(Object value){
  Optional<?> narrowed=narrow(value);
  return narrowed.map(componentGenerators().get(0)::magnitude).orElse(ZERO);
}
