public OkCoinPositionResult getFuturesPosition(String symbol,String contract) throws IOException {
  OkCoinPositionResult futuresPositionsCross=okCoin.getFuturesPositionsCross(apikey,symbol,contract,signatureCreator());
  return returnOrThrow(futuresPositionsCross);
}
