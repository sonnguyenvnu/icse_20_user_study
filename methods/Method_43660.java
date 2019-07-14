/** 
 * @return The Coinbase transaction id for the newly created withdrawal. See {@link CoinbaseAccountServiceRaw#getCoinbaseTransaction(String transactionIdOrIdemField)} toretrieve more information about the transaction, including the blockchain transaction hash.
 */
@Override public String withdrawFunds(Currency currency,BigDecimal amount,String address) throws IOException {
  final CoinbaseSendMoneyRequest sendMoneyRequest=CoinbaseTransaction.createSendMoneyRequest(address,currency.toString(),amount);
  final CoinbaseTransaction sendMoneyTransaction=super.sendMoneyCoinbaseRequest(sendMoneyRequest);
  return sendMoneyTransaction.getId();
}
