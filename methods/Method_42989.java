public Map<String,AssetDetail> getAssetDetails() throws IOException {
  try {
    return super.requestAssetDetail().getAssetDetail();
  }
 catch (  BinanceException e) {
    throw BinanceErrorAdapter.adapt(e);
  }
}
