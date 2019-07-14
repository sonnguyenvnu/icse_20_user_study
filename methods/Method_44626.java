public BigDecimal calculateFeeUsd(){
  return calculateFeeBtc().multiply(price);
}
