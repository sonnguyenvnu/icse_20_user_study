public static Wallet adaptWallet(BTCTradeBalance balance){
  checkException(balance);
  List<Balance> balances=new ArrayList<>(5);
  balances.add(new Balance(Currency.BTC,nullSafeSum(balance.getBtcBalance(),balance.getBtcReserved()),zeroIfNull(balance.getBtcBalance()),zeroIfNull(balance.getBtcReserved())));
  balances.add(new Balance(Currency.LTC,nullSafeSum(balance.getLtcBalance(),balance.getLtcReserved()),zeroIfNull(balance.getLtcBalance()),zeroIfNull(balance.getLtcReserved())));
  balances.add(new Balance(Currency.DOGE,nullSafeSum(balance.getDogeBalance(),balance.getDogeReserved()),zeroIfNull(balance.getDogeBalance()),zeroIfNull(balance.getDogeReserved())));
  balances.add(new Balance(Currency.YBC,nullSafeSum(balance.getYbcBalance(),balance.getYbcReserved()),zeroIfNull(balance.getYbcBalance()),zeroIfNull(balance.getYbcReserved())));
  balances.add(new Balance(Currency.CNY,nullSafeSum(balance.getCnyBalance(),balance.getCnyReserved()),zeroIfNull(balance.getCnyBalance()),zeroIfNull(balance.getCnyReserved())));
  return new Wallet(balances);
}
