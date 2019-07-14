private static void printCandles(List<HitbtcCandle> candles){
  System.out.println("----------------------------------------------------------------------------------------");
  System.out.printf("%-30s %-15s %-15s %-15s %-15s \n","Timestamp","Open","Max","Min","Close");
  System.out.println("----------------------------------------------------------------------------------------");
  for (  HitbtcCandle candle : candles) {
    System.out.printf("%-30s %-15s %-15s %-15s %-15s \n",candle.getTimestamp(),candle.getOpen(),candle.getMax(),candle.getMin(),candle.getClose());
  }
  System.out.println("----------------------------------------------------------------------------------------");
  System.out.println();
  System.out.println();
}
