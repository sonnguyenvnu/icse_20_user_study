/** 
 * Adapts BitMarketBalance to Wallet
 * @param balance
 * @return
 */
public static Wallet adaptWallet(BitMarketBalance balance){
  List<Balance> balances=new ArrayList<>(balance.getAvailable().size());
  for (  Map.Entry<String,BigDecimal> entry : balance.getAvailable().entrySet()) {
    Currency currency=Currency.getInstance(entry.getKey());
    BigDecimal frozen=balance.getBlocked().containsKey(entry.getKey()) ? balance.getBlocked().get(entry.getKey()) : new BigDecimal("0");
    BigDecimal available=entry.getValue();
    balances.add(new Balance(currency,available.add(frozen),available,frozen));
  }
  return new Wallet(balances);
}
