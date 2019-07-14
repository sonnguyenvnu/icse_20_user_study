public CoinWithdrawHistory coinWithdrawHistory(long coinId,Long pageNum,Long pageSize) throws IOException {
  String token=exchange.getOrCreateToken().token;
  DragonResult<CoinWithdrawHistory> coinWithdrawHistory=exchange.dragonexAuthenticated().coinWithdrawHistory(utcNow(),token,exchange.signatureCreator(),ContentSHA1,coinId,pageNum,pageSize);
  return coinWithdrawHistory.getResult();
}
