protected String delimitAssets(Currency[] assets) throws IOException {
  StringBuilder commaDelimitedAssets=new StringBuilder();
  if (assets != null && assets.length > 0) {
    boolean started=false;
    for (    Currency asset : assets) {
      commaDelimitedAssets.append((started) ? "," : "").append(KrakenUtils.getKrakenCurrencyCode(asset));
      started=true;
    }
    return commaDelimitedAssets.toString();
  }
  return null;
}
