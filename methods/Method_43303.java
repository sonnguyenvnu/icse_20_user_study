/** 
 * This returns the currently set deposit address. It will not generate a new address (ie. repeated calls will return the same address).
 */
@Override public String requestDepositAddress(Currency currency,String... arguments) throws IOException {
  final BitsoDepositAddress response=getBitsoBitcoinDepositAddress();
  return response.getDepositAddress();
}
