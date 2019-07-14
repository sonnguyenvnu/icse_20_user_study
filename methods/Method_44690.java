/** 
 * ????????OKEX?????? ????
 * @param symbol
 * @param contract
 * @return
 * @throws IOException
 */
public OkCoinPositionResult getFuturesPosition(String symbol,FuturesContract contract) throws IOException {
  OkCoinPositionResult futuresPositionsCross=okCoin.getFuturesPositionsCross(apikey,symbol,contract.getName(),signatureCreator());
  return returnOrThrow(futuresPositionsCross);
}
