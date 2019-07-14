public List<WithdrawalAddress> coinWithdrawAddrList(long coinId) throws IOException {
  String token=exchange.getOrCreateToken().token;
  DragonResult<List<WithdrawalAddress>> coinWithdrawHistory=exchange.dragonexAuthenticated().coinWithdrawAddrList(utcNow(),token,exchange.signatureCreator(),ContentSHA1,coinId);
  return coinWithdrawHistory.getResult();
}
