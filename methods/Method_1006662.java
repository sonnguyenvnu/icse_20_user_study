@Override public ReadableProductPrice populate(FinalPrice source,ReadableProductPrice target,MerchantStore store,Language language) throws ConversionException {
  Validate.notNull(pricingService,"pricingService must be set");
  try {
    target.setOriginalPrice(pricingService.getDisplayAmount(source.getOriginalPrice(),store));
    if (source.isDiscounted()) {
      target.setDiscounted(true);
      target.setFinalPrice(pricingService.getDisplayAmount(source.getDiscountedPrice(),store));
    }
 else {
      target.setFinalPrice(pricingService.getDisplayAmount(source.getFinalPrice(),store));
    }
  }
 catch (  Exception e) {
    throw new ConversionException("Exception while converting to ReadableProductPrice",e);
  }
  return target;
}
