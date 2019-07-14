private static void rawLocal(WexExchange exchange) throws IOException {
  WexMetaData wexMetaData=exchange.getWexMetaData();
  System.out.println("Wex local meta data: amountScale=" + wexMetaData.amountScale + " public data TTL seconds" + wexMetaData.publicInfoCacheSeconds);
}
