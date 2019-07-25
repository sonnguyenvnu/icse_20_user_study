@Override public BigDecimal inv(Object d){
  if (myContext == null) {
    return BigDecimal.valueOf(1.0).divide(cast(d));
  }
 else   return BigDecimal.valueOf(1.0).divide(cast(d),myContext);
}
