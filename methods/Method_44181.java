private static void raw(ParibuMarketDataService marketDataService) throws IOException {
  ParibuTicker paribuTicker=marketDataService.getParibuTicker();
  System.out.println(paribuTicker.toString());
}
