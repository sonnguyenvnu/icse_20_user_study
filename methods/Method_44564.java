public String walletAddress(Currency currency) throws IOException {
  LivecoinWalletAddressResponse response=service.paymentAddress(apiKey,signatureCreator,currency.getCurrencyCode());
  return response.getWallet();
}
