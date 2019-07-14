/** 
 * Adapts a CryptonitBalance to an AccountInfo
 * @param cryptonitBalance The Cryptonit balance
 * @param userName The user name
 * @return The account info
 */
public static AccountInfo adaptAccountInfo(CryptonitBalance cryptonitBalance,String userName){
  List<Balance> balances=new ArrayList<>();
  for (  CryptonitBalance.Balance b : cryptonitBalance.getBalances()) {
    Balance xchangeBalance=new Balance(Currency.getInstance(b.getCurrency().toUpperCase()),b.getBalance(),b.getAvailable(),b.getReserved(),ZERO,ZERO,b.getBalance().subtract(b.getAvailable()).subtract(b.getReserved()),ZERO);
    balances.add(xchangeBalance);
  }
  return new AccountInfo(userName,cryptonitBalance.getFee(),new Wallet(balances));
}
