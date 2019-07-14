public List<LykkeWallet> getWallets() throws IOException {
  try {
    return lykke.getWallets(apiKey);
  }
 catch (  LykkeException e) {
    throw new ExchangeException(e.getMessage());
  }
}
