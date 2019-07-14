public static Wallet adaptBleutradeBalances(List<BleutradeBalance> bleutradeBalances){
  List<Balance> balances=new ArrayList<>();
  for (  BleutradeBalance bleutradeBalance : bleutradeBalances) {
    balances.add(new Balance(Currency.getInstance(bleutradeBalance.getCurrency()),bleutradeBalance.getBalance(),bleutradeBalance.getAvailable(),bleutradeBalance.getPending()));
  }
  return new Wallet(null,balances);
}
