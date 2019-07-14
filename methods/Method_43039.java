public BitcoinAverageTickers getBitcoinAverageShortTickers(String crypto) throws IOException {
  BitcoinAverageTickers bitcoinAverageTicker=bitcoinAverage.getShortTickers(crypto);
  return bitcoinAverageTicker;
}
