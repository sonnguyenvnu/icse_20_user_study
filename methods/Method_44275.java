public LoanOrderBook getLendOrderBook(String currency,Object... args) throws IOException {
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
  GeminiLendDepth GeminiLendDepth=getGeminiLendBook(currency,limitBids,limitAsks);
  List<FixedRateLoanOrder> fixedRateAsks=GeminiAdapters.adaptFixedRateLoanOrders(GeminiLendDepth.getAsks(),currency,"ask","");
  List<FixedRateLoanOrder> fixedRateBids=GeminiAdapters.adaptFixedRateLoanOrders(GeminiLendDepth.getBids(),currency,"bid","");
  List<FloatingRateLoanOrder> floatingRateAsks=GeminiAdapters.adaptFloatingRateLoanOrders(GeminiLendDepth.getAsks(),currency,"ask","");
  List<FloatingRateLoanOrder> floatingRateBids=GeminiAdapters.adaptFloatingRateLoanOrders(GeminiLendDepth.getBids(),currency,"bid","");
  return new LoanOrderBook(null,fixedRateAsks,fixedRateBids,floatingRateAsks,floatingRateBids);
}
