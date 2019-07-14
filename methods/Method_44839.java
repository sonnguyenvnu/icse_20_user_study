/** 
 * Retrieve order details from local store if they have been previously stored otherwise query external server.
 */
public IRippleTradeTransaction getTrade(final String account,final RippleNotification notification) throws RippleException, IOException {
  final RippleExchange ripple=(RippleExchange)exchange;
  if (ripple.isStoreTradeTransactionDetails()) {
    Map<String,IRippleTradeTransaction> cache=rawTradeStore.get(account);
    if (cache == null) {
      cache=new ConcurrentHashMap<>();
      rawTradeStore.put(account,cache);
    }
    if (cache.containsKey(notification.getHash())) {
      return cache.get(notification.getHash());
    }
  }
  final IRippleTradeTransaction trade;
  try {
    if (notification.getType().equals("order")) {
      trade=ripplePublic.orderTransaction(account,notification.getHash());
    }
 else     if (notification.getType().equals("payment")) {
      trade=ripplePublic.paymentTransaction(account,notification.getHash());
    }
 else {
      throw new IllegalArgumentException(String.format("unexpected notification %s type for transaction[%s] and account[%s]",notification.getType(),notification.getHash(),notification.getAccount()));
    }
  }
 catch (  final RippleException e) {
    if (e.getHttpStatusCode() == 500 && e.getErrorType().equals("transaction")) {
      logger.error("exception reading {} transaction[{}] for account[{}]",notification.getType(),notification.getHash(),account,e);
      return null;
    }
 else {
      throw e;
    }
  }
  if (ripple.isStoreTradeTransactionDetails()) {
    rawTradeStore.get(account).put(notification.getHash(),trade);
  }
  return trade;
}
