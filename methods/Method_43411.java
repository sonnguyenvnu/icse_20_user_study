public static final BigDecimal fromSatoshi(BigDecimal bd){
  return bd.divide(SATOSHI);
}
