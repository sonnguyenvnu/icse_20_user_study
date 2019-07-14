public BigDecimal getTotalFee(){
  Function<BankeraTransaction,BigDecimal> totalMapper=tx -> new BigDecimal(tx.getFee());
  return transactions.size() > 0 ? transactions.stream().map(totalMapper).reduce(BigDecimal.ZERO,BigDecimal::add) : BigDecimal.ZERO;
}
