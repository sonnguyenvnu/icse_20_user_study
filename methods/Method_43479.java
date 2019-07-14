public BTCTradePlaceOrderResult sell(BigDecimal amount,BigDecimal price) throws IOException {
synchronized (session) {
    return btcTrade.sell(amount.toPlainString(),price.toPlainString(),exchange.getNonceFactory(),publicKey,getSignatureCreator());
  }
}
