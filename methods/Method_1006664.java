@Override public ReadableProductPrice populate(ProductPrice source,ReadableProductPrice target,MerchantStore store,Language language) throws ConversionException {
  Validate.notNull(pricingService,"pricingService must be set");
  Validate.notNull(source.getProductAvailability(),"productPrice.availability cannot be null");
  Validate.notNull(source.getProductAvailability().getProduct(),"productPrice.availability.product cannot be null");
  try {
    FinalPrice finalPrice=pricingService.calculateProductPrice(source.getProductAvailability().getProduct());
    target.setOriginalPrice(pricingService.getDisplayAmount(source.getProductPriceAmount(),store));
    if (finalPrice.isDiscounted()) {
      target.setDiscounted(true);
      target.setFinalPrice(pricingService.getDisplayAmount(source.getProductPriceSpecialAmount(),store));
    }
 else {
      target.setFinalPrice(pricingService.getDisplayAmount(finalPrice.getOriginalPrice(),store));
    }
  }
 catch (  Exception e) {
    throw new ConversionException("Exception while converting to ReadableProductPrice",e);
  }
  return target;
}
