public Token tokenNew() throws DragonexException, IOException {
  DragonResult<Token> tokenNew=exchange.dragonexAuthenticated().tokenNew(utcNow(),exchange.signatureCreator(),ContentSHA1);
  return tokenNew.getResult();
}
