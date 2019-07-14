public AssetDetailResponse requestAssetDetail() throws IOException {
  Long recvWindow=(Long)exchange.getExchangeSpecification().getExchangeSpecificParametersItem("recvWindow");
  return binance.assetDetail(recvWindow,getTimestamp(),apiKey,super.signatureCreator);
}
