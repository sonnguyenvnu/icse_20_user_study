@Override public BigDecimal cast(Object o){
  if (o instanceof BigDecimal) {
    return (BigDecimal)o;
  }
  if (o instanceof BigInteger) {
    return new BigDecimal((BigInteger)o);
  }
  if (o instanceof Number) {
    return BigDecimal.valueOf(((Number)o).doubleValue());
  }
  throw new ClassCastException();
}
