public List getAllWallets(){
  return itBitAuthenticated.wallets(signatureCreator,exchange.getNonceFactory(),new Date().getTime(),userId,1,50);
}
