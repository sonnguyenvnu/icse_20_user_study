public BigDecimal calculateFeeBtc(){
  return roundUp(amount.multiply(new BigDecimal(.5))).divide(new BigDecimal(100.));
}
