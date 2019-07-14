public LoanOrderBook getLendOrderBook(String currency,Object... args) throws IOException {
  try {
    int limitBids=50;
    int limitAsks=50;
    if (args != null && args.length == 2) {
      Object arg0=args[0];
      if (!(arg0 instanceof Integer)) {
        throw new ExchangeException("Argument 0 must be an Integer!");
      }
 else {
        limitBids=(Integer)arg0;
      }
      Object arg1=args[1];
      if (!(arg1 instanceof Integer)) {
        throw new ExchangeException("Argument 1 must be an Integer!");
      }
 else {
        limitAsks=(Integer)arg1;
      }
    }
    BitfinexLendDepth bitfinexLendDepth=getBitfinexLendBook(currency,limitBids,limitAsks);
    List<FixedRateLoanOrder> fixedRateAsks=BitfinexAdapters.adaptFixedRateLoanOrders(bitfinexLendDepth.getAsks(),currency,"ask","");
    List<FixedRateLoanOrder> fixedRateBids=BitfinexAdapters.adaptFixedRateLoanOrders(bitfinexLendDepth.getBids(),currency,"bid","");
    List<FloatingRateLoanOrder> floatingRateAsks=BitfinexAdapters.adaptFloatingRateLoanOrders(bitfinexLendDepth.getAsks(),currency,"ask","");
    List<FloatingRateLoanOrder> floatingRateBids=BitfinexAdapters.adaptFloatingRateLoanOrders(bitfinexLendDepth.getBids(),currency,"bid","");
    return new LoanOrderBook(null,fixedRateAsks,fixedRateBids,floatingRateAsks,floatingRateBids);
  }
 catch (  BitfinexException e) {
    throw BitfinexErrorAdapter.adapt(e);
  }
}
