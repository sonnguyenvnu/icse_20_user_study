public static AccountInfo adaptAccountInfo(List<LykkeWallet> lykkeWallets){
  List<Balance> balances=new ArrayList<>();
  for (  LykkeWallet lykkeWallet : lykkeWallets) {
    balances.add(new Balance(new Currency(lykkeWallet.getAssetId()),new BigDecimal(lykkeWallet.getBalance()).setScale(8,RoundingMode.HALF_EVEN).stripTrailingZeros(),new BigDecimal(lykkeWallet.getBalance() - lykkeWallet.getReserved()).setScale(8,RoundingMode.HALF_EVEN).stripTrailingZeros(),new BigDecimal(lykkeWallet.getReserved()).setScale(8,RoundingMode.HALF_EVEN).stripTrailingZeros()));
  }
  return new AccountInfo(new Wallet("apiWallet","apiWallet",balances));
}
