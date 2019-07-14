public List<OkexSpotAccountRecord> spotTradingAccount() throws IOException {
  return okex.spotTradingAccount(apikey,digest,timestamp(),passphrase);
}
