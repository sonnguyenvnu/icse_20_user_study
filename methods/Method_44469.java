public KrakenOrderResponse placeKrakenOrder(KrakenStandardOrder krakenStandardOrder) throws IOException {
  KrakenOrderResult result=null;
  if (!krakenStandardOrder.isValidateOnly()) {
    result=kraken.addOrder(KrakenUtils.createKrakenCurrencyPair(krakenStandardOrder.getAssetPair()),krakenStandardOrder.getType().toString(),krakenStandardOrder.getOrderType().toApiFormat(),krakenStandardOrder.getPrice(),krakenStandardOrder.getSecondaryPrice(),krakenStandardOrder.getVolume().toPlainString(),krakenStandardOrder.getLeverage(),krakenStandardOrder.getPositionTxId(),delimitSet(krakenStandardOrder.getOrderFlags()),krakenStandardOrder.getStartTime(),krakenStandardOrder.getExpireTime(),krakenStandardOrder.getUserRefId(),krakenStandardOrder.getCloseOrder(),exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
  }
 else {
    result=kraken.addOrderValidateOnly(KrakenUtils.createKrakenCurrencyPair(krakenStandardOrder.getAssetPair()),krakenStandardOrder.getType().toString(),krakenStandardOrder.getOrderType().toApiFormat(),krakenStandardOrder.getPrice(),krakenStandardOrder.getSecondaryPrice(),krakenStandardOrder.getVolume().toPlainString(),krakenStandardOrder.getLeverage(),krakenStandardOrder.getPositionTxId(),delimitSet(krakenStandardOrder.getOrderFlags()),krakenStandardOrder.getStartTime(),krakenStandardOrder.getExpireTime(),krakenStandardOrder.getUserRefId(),true,krakenStandardOrder.getCloseOrder(),exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
  }
  return checkResult(result);
}
