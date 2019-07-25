@Override public BigDecimal magnitude(Object value){
  Optional<?> narrowed=narrow(value);
  return narrowed.toJavaUtil().map(componentGenerators().get(0)::magnitude).orElse(ZERO);
}
