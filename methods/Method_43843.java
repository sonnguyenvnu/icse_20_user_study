@Override public void remoteInit() throws IOException, ExchangeException {
  CoinmateMetadataServiceRaw metadataService=new CoinmateMetadataServiceRaw(this);
  exchangeMetaData=metadataService.getMetadata();
}
