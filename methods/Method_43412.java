public static final long toSatoshi(BigDecimal bd){
  return bd.multiply(SATOSHI).longValue();
}
