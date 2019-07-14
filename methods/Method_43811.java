public CoingiBalances getCoingiBalance() throws IOException {
  CoingiBalanceRequest balanceRequest=new CoingiBalanceRequest();
  handleAuthentication(balanceRequest);
  balanceRequest.setCurrencies("btc,ltc,ppc,doge,vtc,nmc,dash,usd,eur,czk");
  return coingiAuthenticated.getUserBalance(balanceRequest);
}
