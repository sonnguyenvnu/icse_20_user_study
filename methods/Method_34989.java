Ticker getTicker(boolean recordsTime){
  if (ticker != null) {
    return ticker;
  }
  return recordsTime ? Ticker.systemTicker() : NULL_TICKER;
}
