public String placeBiboxMarketOrder(MarketOrder marketOrder){
  try {
    BiboxTradeCommand cmd=new BiboxTradeCommand(BiboxAdapters.toBiboxPair(marketOrder.getCurrencyPair()),BiboxAccountType.REGULAR.asInt(),BiboxOrderType.MARKET_ORDER.asInt(),BiboxOrderSide.fromOrderType(marketOrder.getType()).asInt(),true,null,marketOrder.getOriginalAmount(),null);
    BiboxSingleResponse<String> response=bibox.trade(BiboxCommands.of(cmd).json(),apiKey,signatureCreator);
    throwErrors(response);
    return response.get().getResult();
  }
 catch (  BiboxException e) {
    throw new ExchangeException(e.getMessage());
  }
}
