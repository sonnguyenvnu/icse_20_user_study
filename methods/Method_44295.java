private static String convertBTCtoXBT(String symbol){
  return (symbol.contains("BTC")) ? symbol.replace("BTC","XBT") : symbol;
}
