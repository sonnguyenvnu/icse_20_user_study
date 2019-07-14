static BitcointoyouLevel adaptRawBitcointoyouLevel(List<BigDecimal> rawLevel){
  return new BitcointoyouLevel(rawLevel.get(0),rawLevel.get(1));
}
