public Wallet balances(String currency) throws IOException {
  List<LivecoinBalance> response=service.balances(apiKey,signatureCreator,currency);
  return LivecoinAdapters.adaptWallet(response);
}
