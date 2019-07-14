public BigDecimal getFeeAmount(){
  return feeTransaction == null ? BigDecimal.ZERO : feeTransaction.price;
}
