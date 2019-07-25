@Override public Payment populate(PersistablePayment source,Payment target,MerchantStore store,Language language) throws ConversionException {
  Validate.notNull(source,"PersistablePayment cannot be null");
  Validate.notNull(pricingService,"pricingService must be set");
  if (target == null) {
    target=new Payment();
  }
  try {
    target.setAmount(pricingService.getAmount(source.getAmount()));
    target.setModuleName(source.getPaymentModule());
    target.setPaymentType(PaymentType.valueOf(source.getPaymentType()));
    target.setTransactionType(TransactionType.valueOf(source.getTransactionType()));
    Map<String,String> metadata=new HashMap<String,String>();
    metadata.put("paymentToken",source.getPaymentToken());
    target.setPaymentMetaData(metadata);
    return target;
  }
 catch (  Exception e) {
    throw new ConversionException(e);
  }
}
