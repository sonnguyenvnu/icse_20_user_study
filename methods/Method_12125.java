public BigDecimal getTotalAmount(){
  return usableAmount.add(freezedAmount).add(unReceivePrincipal);
}
