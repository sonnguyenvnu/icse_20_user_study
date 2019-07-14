public DSXFeesResult getFees() throws IOException {
  DSXFeesReturn res=dsx.getFees(apiKey,signatureCreator,exchange.getNonceFactory());
  if (MSG_BAD_STATUS.equals(res.getError())) {
    return null;
  }
  checkResult(res);
  return res.getReturnValue();
}
