/** 
 * Converts a ShoppingCartItem carried in the ShoppingCart to an OrderProduct that will be saved in the system
 */
@Override public OrderProduct populate(ShoppingCartItem source,OrderProduct target,MerchantStore store,Language language) throws ConversionException {
  Validate.notNull(productService,"productService must be set");
  Validate.notNull(digitalProductService,"digitalProductService must be set");
  Validate.notNull(productAttributeService,"productAttributeService must be set");
  try {
    Product modelProduct=productService.getById(source.getProductId());
    if (modelProduct == null) {
      throw new ConversionException("Cannot get product with id (productId) " + source.getProductId());
    }
    if (modelProduct.getMerchantStore().getId().intValue() != store.getId().intValue()) {
      throw new ConversionException("Invalid product id " + source.getProductId());
    }
    DigitalProduct digitalProduct=digitalProductService.getByProduct(store,modelProduct);
    if (digitalProduct != null) {
      OrderProductDownload orderProductDownload=new OrderProductDownload();
      orderProductDownload.setOrderProductFilename(digitalProduct.getProductFileName());
      orderProductDownload.setOrderProduct(target);
      orderProductDownload.setDownloadCount(0);
      orderProductDownload.setMaxdays(ApplicationConstants.MAX_DOWNLOAD_DAYS);
      target.getDownloads().add(orderProductDownload);
    }
    target.setOneTimeCharge(source.getItemPrice());
    target.setProductName(source.getProduct().getDescriptions().iterator().next().getName());
    target.setProductQuantity(source.getQuantity());
    target.setSku(source.getProduct().getSku());
    FinalPrice finalPrice=source.getFinalPrice();
    if (finalPrice == null) {
      throw new ConversionException("Object final price not populated in shoppingCartItem (source)");
    }
    OrderProductPrice orderProductPrice=orderProductPrice(finalPrice);
    orderProductPrice.setOrderProduct(target);
    Set<OrderProductPrice> prices=new HashSet<OrderProductPrice>();
    prices.add(orderProductPrice);
    List<FinalPrice> otherPrices=finalPrice.getAdditionalPrices();
    if (otherPrices != null) {
      for (      FinalPrice otherPrice : otherPrices) {
        OrderProductPrice other=orderProductPrice(otherPrice);
        other.setOrderProduct(target);
        prices.add(other);
      }
    }
    target.setPrices(prices);
    Set<ShoppingCartAttributeItem> attributeItems=source.getAttributes();
    if (!CollectionUtils.isEmpty(attributeItems)) {
      Set<OrderProductAttribute> attributes=new HashSet<OrderProductAttribute>();
      for (      ShoppingCartAttributeItem attribute : attributeItems) {
        OrderProductAttribute orderProductAttribute=new OrderProductAttribute();
        orderProductAttribute.setOrderProduct(target);
        Long id=attribute.getProductAttributeId();
        ProductAttribute attr=productAttributeService.getById(id);
        if (attr == null) {
          throw new ConversionException("Attribute id " + id + " does not exists");
        }
        if (attr.getProduct().getMerchantStore().getId().intValue() != store.getId().intValue()) {
          throw new ConversionException("Attribute id " + id + " invalid for this store");
        }
        orderProductAttribute.setProductAttributeIsFree(attr.getProductAttributeIsFree());
        orderProductAttribute.setProductAttributeName(attr.getProductOption().getDescriptionsSettoList().get(0).getName());
        orderProductAttribute.setProductAttributeValueName(attr.getProductOptionValue().getDescriptionsSettoList().get(0).getName());
        orderProductAttribute.setProductAttributePrice(attr.getProductAttributePrice());
        orderProductAttribute.setProductAttributeWeight(attr.getProductAttributeWeight());
        orderProductAttribute.setProductOptionId(attr.getProductOption().getId());
        orderProductAttribute.setProductOptionValueId(attr.getProductOptionValue().getId());
        attributes.add(orderProductAttribute);
      }
      target.setOrderAttributes(attributes);
    }
  }
 catch (  Exception e) {
    throw new ConversionException(e);
  }
  return target;
}
