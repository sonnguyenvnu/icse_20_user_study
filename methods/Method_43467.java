public static String adaptDepositAddress(BTCTradeWallet wallet){
  checkException(wallet);
  return wallet.getAddress();
}
