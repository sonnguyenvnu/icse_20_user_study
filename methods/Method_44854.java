private Ticker.Builder newTickerFromBook(){
  return new Ticker.Builder().ask(asks.isEmpty() ? null : asks.get(0).getPrice()).bid(bids.isEmpty() ? null : bids.get(0).getPrice());
}
