public synchronized OrderBook level2(){
  return new OrderBook(new Date(),accumulateBookSide(asks),accumulateBookSide(bids));
}
