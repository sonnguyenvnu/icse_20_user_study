public DSXCancelAllOrdersResult cancelAllDSXOrders() throws IOException {
  DSXCancelAllOrdersReturn ret=dsx.cancelAllOrders(apiKey,signatureCreator,exchange.getNonceFactory());
  if (MSG_BAD_STATUS.equals(ret.getError())) {
    return null;
  }
  checkResult(ret);
  return ret.getReturnValue();
}
