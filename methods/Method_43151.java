public static Fee adaptTradingCommission(BitflyerTradingCommission commission){
  return new Fee(commission.getCommissionRate(),commission.getCommissionRate());
}
