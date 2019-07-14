private void refresh() throws IOException {
  secretData=secretDataService.getSecretData();
  secretExpiresTime=BTCTradeAdapters.adaptDatetime(secretData.getExpires()).getTime();
  signatureCreator=BTCTradeDigest.createInstance(secretData.getSecret());
}
