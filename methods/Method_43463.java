private static void checkException(BTCTradeResult result){
  if (!result.isSuccess()) {
    throw new ExchangeException(result.getMessage());
  }
}
