public BigDecimal calculateFeeMxn(){
  return calculateFeeBtc().multiply(price);
}
