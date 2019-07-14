public List<OkexFundingAccountRecord> fundingAccountInformation() throws IOException {
  return okex.fundingAccountInformation(apikey,digest,timestamp(),passphrase);
}
