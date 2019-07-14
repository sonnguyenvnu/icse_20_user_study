private List<LimitOrder> accumulateBookSide(List<BookLevel> book){
  BigDecimal price=null;
  BigDecimal amount=ZERO;
  List<LimitOrder> result=new ArrayList<>();
  Iterator<BookOrder> iter=book.stream().flatMap(v -> v.getOrders().stream()).iterator();
  while (iter.hasNext()) {
    BookOrder bookOrder=iter.next();
    amount=amount.add(bookOrder.getRemainingAmount());
    if (price != null && bookOrder.getLimitPrice().compareTo(price) != 0) {
      result.add(new LimitOrder.Builder(ASK,currencyPair).originalAmount(amount).limitPrice(price).build());
      amount=ZERO;
    }
    price=bookOrder.getLimitPrice();
  }
  if (price != null) {
    result.add(new LimitOrder.Builder(ASK,currencyPair).originalAmount(amount).limitPrice(price).build());
  }
  return result;
}
