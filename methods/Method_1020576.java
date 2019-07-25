public void book(int bookingCnt){
  for (int i=0; i < bookingCnt && overStockItems.size() < OVER_STOCK_MAX_SIZE; i++) {
    T obj=produce(pool.acquire());
    if (!availableBalloons.putNewProductions(obj)) {
      overStockItems.add(obj);
    }
  }
}
