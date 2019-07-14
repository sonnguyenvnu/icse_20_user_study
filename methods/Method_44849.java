boolean matches(BookOrder takerOrder){
  return type == ASK ? limitPrice.compareTo(takerOrder.getLimitPrice()) <= 0 : limitPrice.compareTo(takerOrder.getLimitPrice()) >= 0;
}
