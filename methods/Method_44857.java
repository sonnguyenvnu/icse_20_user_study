private void chewBook(Iterable<BookLevel> makerOrders,BookOrder takerOrder){
  Iterator<BookLevel> levelIter=makerOrders.iterator();
  while (levelIter.hasNext()) {
    BookLevel level=levelIter.next();
    Iterator<BookOrder> orderIter=level.getOrders().iterator();
    while (orderIter.hasNext() && !takerOrder.isDone()) {
      BookOrder makerOrder=orderIter.next();
      LOGGER.debug("Matching against maker order {}",makerOrder);
      if (!makerOrder.matches(takerOrder)) {
        LOGGER.debug("Ran out of maker orders at this price");
        return;
      }
      BigDecimal tradeAmount=takerOrder.getRemainingAmount().compareTo(makerOrder.getRemainingAmount()) > 0 ? makerOrder.getRemainingAmount() : takerOrder.getRemainingAmount();
      LOGGER.debug("Matches for {}",tradeAmount);
      matchOff(takerOrder,makerOrder,tradeAmount);
      if (makerOrder.isDone()) {
        LOGGER.debug("Maker order removed from book");
        orderIter.remove();
        if (level.getOrders().isEmpty()) {
          levelIter.remove();
        }
      }
    }
  }
}
