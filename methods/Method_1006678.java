@Override public Transaction populate(PersistableTransaction source,Transaction target,MerchantStore store,Language language) throws ConversionException {
  Validate.notNull(source,"PersistableTransaction must not be null");
  Validate.notNull(orderService,"OrderService must not be null");
  Validate.notNull(pricingService,"OrderService must not be null");
  if (target == null) {
    target=new Transaction();
  }
  try {
    target.setAmount(pricingService.getAmount(source.getAmount()));
    target.setDetails(source.getDetails());
    target.setPaymentType(PaymentType.valueOf(source.getPaymentType()));
    target.setTransactionType(TransactionType.valueOf(source.getTransactionType()));
    target.setTransactionDate(DateUtil.formatDate(source.getTransactionDate()));
    if (source.getOrderId() != null && source.getOrderId().longValue() > 0) {
      Order order=orderService.getById(source.getOrderId());
      if (order == null) {
        throw new ConversionException("Order with id " + source.getOrderId() + "does not exist");
      }
      target.setOrder(order);
    }
    return target;
  }
 catch (  Exception e) {
    throw new ConversionException(e);
  }
}
