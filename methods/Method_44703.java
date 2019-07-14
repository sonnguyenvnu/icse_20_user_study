private static BigDecimal stripTrailingZeros(BigDecimal bd){
  bd=bd.stripTrailingZeros();
  bd=bd.setScale(Math.max(bd.scale(),0));
  return bd;
}
