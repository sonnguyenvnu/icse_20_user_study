/** 
 * @param coinsuperTicker
 * @return
 */
public static Ticker convertTicker(CoinsuperTicker coinsuperTicker){
  return new Ticker.Builder().ask(coinsuperTicker.getPrice()).bid(coinsuperTicker.getPrice()).high(coinsuperTicker.getPrice()).low(coinsuperTicker.getPrice()).volume(coinsuperTicker.getVolume()).last(coinsuperTicker.getPrice()).timestamp(CommonUtil.timeStampToDate(coinsuperTicker.getTimestamp())).build();
}
