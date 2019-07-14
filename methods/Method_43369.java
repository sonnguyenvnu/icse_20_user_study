public List<BittrexChartData> getBittrexLatestTick(CurrencyPair currencyPair,BittrexChartDataPeriodType periodType,Long timeStamp) throws IOException {
  return bittrexV2.getLatestTick(BittrexUtils.toPairString(currencyPair),periodType.getPeriod(),timeStamp).getResult();
}
