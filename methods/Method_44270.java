/** 
 * This will result in a new address being created each time, and is severely rate-limited 
 */
@Override public String requestDepositAddress(Currency currency,String... arguments) throws IOException {
  GeminiDepositAddressResponse response=super.requestDepositAddressRaw(currency);
  return response.getAddress();
}
