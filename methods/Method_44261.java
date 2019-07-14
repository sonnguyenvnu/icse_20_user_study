private static OrderStatus adaptOrderstatus(GeminiOrderStatusResponse geminiOrderStatusResponse){
  if (geminiOrderStatusResponse.isCancelled())   return OrderStatus.CANCELED;
  if (geminiOrderStatusResponse.getRemainingAmount().equals(new BigDecimal(0.0)))   return OrderStatus.FILLED;
  if (geminiOrderStatusResponse.getRemainingAmount().compareTo(new BigDecimal(0.0)) > 0)   return OrderStatus.PARTIALLY_FILLED;
  throw new NotYetImplementedForExchangeException();
}
