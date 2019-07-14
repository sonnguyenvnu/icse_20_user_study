/** 
 * @param ticker
 * @return
 */
public static Ticker convertTicker(EXXTickerResponse exxTickerResponse){
  EXXTicker ticker=exxTickerResponse.getTicker();
  return new Ticker.Builder().ask(ticker.getSell()).bid(ticker.getBuy()).high(ticker.getHigh()).low(ticker.getLow()).volume(ticker.getVol()).last(ticker.getLast()).timestamp(CommonUtil.timeStampToDate(exxTickerResponse.getDate())).build();
}
