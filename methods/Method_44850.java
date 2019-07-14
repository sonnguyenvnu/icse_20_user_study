public synchronized LimitOrder postOrder(String apiKey,Order original){
  LOGGER.debug("User {} posting order: {}",apiKey,original);
  validate(original);
  Account account=accountFactory.get(apiKey);
  checkBalance(original,account);
  BookOrder takerOrder=BookOrder.fromOrder(original,apiKey);
switch (takerOrder.getType()) {
case ASK:
    LOGGER.debug("Matching against bids");
  chewBook(bids,takerOrder);
if (!takerOrder.isDone()) {
  if (original instanceof MarketOrder) {
    throw new ExchangeException("Cannot fulfil order. No buyers.");
  }
  insertIntoBook(asks,takerOrder,ASK,account);
}
break;
case BID:
LOGGER.debug("Matching against asks");
chewBook(asks,takerOrder);
if (!takerOrder.isDone()) {
if (original instanceof MarketOrder) {
throw new ExchangeException("Cannot fulfil order. No sellers.");
}
insertIntoBook(bids,takerOrder,BID,account);
}
break;
default :
throw new ExchangeException("Unsupported order type: " + takerOrder.getType());
}
return takerOrder.toOrder(currencyPair);
}
