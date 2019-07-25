@Override public BigComplex cast(Object o){
  if (o instanceof BigComplex) {
    return (BigComplex)o;
  }
  if (o instanceof Complex) {
    return new BigComplex((Complex)o);
  }
  if (o instanceof BigInteger) {
    return new BigComplex(new BigDecimal((BigInteger)o),BigDecimal.ZERO);
  }
  if (o instanceof BigDecimal) {
    return new BigComplex(((BigDecimal)o),BigDecimal.ZERO);
  }
  if (o instanceof Number) {
    return new BigComplex(((Number)o).doubleValue(),0);
  }
  throw new ClassCastException();
}
