private void insertIntoBook(List<BookLevel> book,BookOrder order,OrderType type,Account account){
  int i=0;
  boolean insert=false;
  Iterator<BookLevel> iter=book.iterator();
  while (iter.hasNext()) {
    BookLevel level=iter.next();
    int signum=level.getPrice().compareTo(order.getLimitPrice());
    if (signum == 0) {
      level.getOrders().add(order);
      return;
    }
 else     if (signum < 0 && type == BID || signum > 0 && type == ASK) {
      insert=true;
      break;
    }
    i++;
  }
  account.reserve(order.toOrder(currencyPair));
  BookLevel newLevel=new BookLevel(order.getLimitPrice());
  newLevel.getOrders().add(order);
  if (insert) {
    book.add(i,newLevel);
  }
 else {
    book.add(newLevel);
  }
  ticker=newTickerFromBook().last(ticker.getLast()).build();
}
