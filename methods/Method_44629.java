/** 
 * Adapts a MercadoBitcoinBaseTradeApiResult<MercadoBitcoinAccountInfo> to an AccountInfo
 * @param accountInfo The Mercado Bitcoin accountInfo
 * @param userName The user name
 * @return The account info
 */
public static AccountInfo adaptAccountInfo(MercadoBitcoinBaseTradeApiResult<MercadoBitcoinAccountInfo> accountInfo,String userName){
  Balance brlBalance=new Balance(Currency.BRL,accountInfo.getTheReturn().getFunds().getBrl());
  Balance btcBalance=new Balance(Currency.BTC,accountInfo.getTheReturn().getFunds().getBtc());
  Balance ltcBalance=new Balance(Currency.LTC,accountInfo.getTheReturn().getFunds().getLtc());
  return new AccountInfo(userName,new Wallet(brlBalance,btcBalance,ltcBalance));
}
