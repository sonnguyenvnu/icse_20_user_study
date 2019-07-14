public List<Balance> userCoins(String token) throws DragonexException, IOException {
  DragonResult<List<Balance>> userCoins=exchange.dragonexAuthenticated().userCoins(utcNow(),token,exchange.signatureCreator(),ContentSHA1);
  return userCoins.getResult();
}
