private static String convertXBTtoBTC(String symbol){
  return (symbol.contains("XBT")) ? symbol.replace("XBT","BTC") : symbol;
}
