private void accumulate(BookOrder bookOrder,UserTrade trade){
  BigDecimal amount=trade.getOriginalAmount();
  BigDecimal price=trade.getPrice();
  BigDecimal newTotal=bookOrder.getCumulativeAmount().add(amount);
  if (bookOrder.getCumulativeAmount().compareTo(ZERO) == 0) {
    bookOrder.setAveragePrice(price);
  }
 else {
    bookOrder.setAveragePrice(bookOrder.getAveragePrice().multiply(bookOrder.getCumulativeAmount()).add(price.multiply(amount)).divide(newTotal,priceScale,HALF_UP));
  }
  bookOrder.setCumulativeAmount(newTotal);
  bookOrder.setFee(bookOrder.getFee().add(trade.getFeeAmount()));
}
