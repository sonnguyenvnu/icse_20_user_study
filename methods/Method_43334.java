protected static BigDecimal calculateFrozenBalance(BittrexBalance balance){
  if (balance.getBalance() == null) {
    return BigDecimal.ZERO;
  }
  final BigDecimal[] frozenBalance={balance.getBalance()};
  Optional.ofNullable(balance.getAvailable()).ifPresent(available -> frozenBalance[0]=frozenBalance[0].subtract(available));
  Optional.ofNullable(balance.getPending()).ifPresent(pending -> frozenBalance[0]=frozenBalance[0].subtract(pending));
  return frozenBalance[0];
}
