public BigDecimal getAvailable(){
  return pendingFunds != null && balance != null ? balance.subtract(pendingFunds) : null;
}
