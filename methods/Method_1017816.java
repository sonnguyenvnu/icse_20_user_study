@Override public BigDecimal magnitude(Object value){
  return BigDecimal.valueOf(narrow(value).size());
}
