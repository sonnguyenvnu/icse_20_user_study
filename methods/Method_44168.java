private static void rawLocal(DSXExchange exchange) throws IOException {
  DSXMetaData dsxMetaData=exchange.getDsxMetaData();
  System.out.println("DSX local meta data: amountScale=" + dsxMetaData.amountScale + " public data TTL seconds" + dsxMetaData.publicInfoCacheSeconds);
}
