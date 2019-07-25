public static boolean deny(HttpExchange t){
  return !PMS.getConfiguration().getIpFiltering().allowed(t.getRemoteAddress().getAddress()) || !PMS.isReady();
}
