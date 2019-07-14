public String placeBiboxLimitOrder(LimitOrder limitOrder){
  try {
    BiboxTradeCommand cmd=new BiboxTradeCommand(BiboxAdapters.toBiboxPair(limitOrder.getCurrencyPair()),BiboxAccountType.REGULAR.asInt(),BiboxOrderType.LIMIT_ORDER.asInt(),BiboxOrderSide.fromOrderType(limitOrder.getType()).asInt(),true,limitOrder.getLimitPrice(),limitOrder.getOriginalAmount(),null);
    BiboxSingleResponse<String> response=bibox.trade(BiboxCommands.of(cmd).json(),apiKey,signatureCreator);
    throwErrors(response);
    return response.get().getResult();
  }
 catch (  BiboxException e) {
    throw new ExchangeException(e.getMessage());
  }
}
