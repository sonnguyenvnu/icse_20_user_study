private static void rawLocal(DSXExchangeV3 exchange) throws IOException {
  DSXMetaData dsxMetaData=exchange.getDsxMetaData();
  System.out.println("DSX local meta data: amountScale=" + dsxMetaData.amountScale + " public data TTL seconds" + dsxMetaData.publicInfoCacheSeconds);
}
