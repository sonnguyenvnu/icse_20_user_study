public CoinPrepayHistory coinPrepayHistory(long coinId,Long pageNum,Long pageSize) throws IOException {
  String token=exchange.getOrCreateToken().token;
  DragonResult<CoinPrepayHistory> coinPrepareHistory=exchange.dragonexAuthenticated().coinPrepayHistory(utcNow(),token,exchange.signatureCreator(),ContentSHA1,coinId,pageNum,pageSize);
  return coinPrepareHistory.getResult();
}
