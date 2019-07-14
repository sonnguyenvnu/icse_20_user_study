public static AccountInfo adaptAccountInfo(CoinEggBalance coinEggBalance,Exchange exchange){
  String userName=exchange.getExchangeSpecification().getUserName();
  Wallet btcWallet=new Wallet(Currency.BTC.getCurrencyCode(),new Balance(Currency.BTC,coinEggBalance.getBTCBalance()));
  Wallet ethWallet=new Wallet(Currency.ETH.getCurrencyCode(),new Balance(Currency.ETH,coinEggBalance.getETHBalance()));
  Set<Wallet> wallets=new HashSet<Wallet>();
  wallets.add(btcWallet);
  wallets.add(ethWallet);
  return new AccountInfo(userName,null,wallets);
}
