/** 
 * Adapts a list of BitflyerBalance objects to Wallet.
 * @param balances Some BitflyerBalances from the API
 * @return A Wallet with balances in it
 */
public static Wallet adaptAccountInfo(List<BitflyerBalance> balances){
  List<Balance> adaptedBalances=new ArrayList<>(balances.size());
  for (  BitflyerBalance balance : balances) {
    adaptedBalances.add(new Balance(Currency.getInstance(balance.getCurrencyCode()),balance.getAmount(),balance.getAvailable()));
  }
  return new Wallet(adaptedBalances);
}
