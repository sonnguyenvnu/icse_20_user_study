@Override public ReadableShoppingCart populate(ShoppingCart source,ReadableShoppingCart target,MerchantStore store,Language language) throws ConversionException {
  Validate.notNull(source,"Requires ShoppingCart");
  Validate.notNull(language,"Requires Language not null");
  Validate.notNull(store,"Requires MerchantStore not null");
  Validate.notNull(pricingService,"Requires to set pricingService");
  Validate.notNull(productAttributeService,"Requires to set productAttributeService");
  Validate.notNull(shoppingCartCalculationService,"Requires to set shoppingCartCalculationService");
  Validate.notNull(imageUtils,"Requires to set imageUtils");
  if (target == null) {
    target=new ReadableShoppingCart();
  }
  target.setCode(source.getShoppingCartCode());
  int cartQuantity=0;
  target.setCustomer(source.getCustomerId());
  try {
    Set<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> items=source.getLineItems();
    if (items != null) {
      for (      com.salesmanager.core.model.shoppingcart.ShoppingCartItem item : items) {
        ReadableShoppingCartItem shoppingCartItem=new ReadableShoppingCartItem();
        ReadableProductPopulator readableProductPopulator=new ReadableProductPopulator();
        readableProductPopulator.setPricingService(pricingService);
        readableProductPopulator.setimageUtils(imageUtils);
        readableProductPopulator.populate(item.getProduct(),shoppingCartItem,store,language);
        shoppingCartItem.setPrice(item.getItemPrice());
        shoppingCartItem.setFinalPrice(pricingService.getDisplayAmount(item.getItemPrice(),store));
        shoppingCartItem.setQuantity(item.getQuantity());
        cartQuantity=cartQuantity + item.getQuantity();
        BigDecimal subTotal=pricingService.calculatePriceQuantity(item.getItemPrice(),item.getQuantity());
        shoppingCartItem.setSubTotal(subTotal);
        shoppingCartItem.setDisplaySubTotal(pricingService.getDisplayAmount(subTotal,store));
        Set<com.salesmanager.core.model.shoppingcart.ShoppingCartAttributeItem> attributes=item.getAttributes();
        if (attributes != null) {
          for (          com.salesmanager.core.model.shoppingcart.ShoppingCartAttributeItem attribute : attributes) {
            ProductAttribute productAttribute=productAttributeService.getById(attribute.getProductAttributeId());
            if (productAttribute == null) {
              LOGGER.warn("Product attribute with ID " + attribute.getId() + " not found, skipping cart attribute " + attribute.getId());
              continue;
            }
            ReadableShoppingCartAttribute cartAttribute=new ReadableShoppingCartAttribute();
            cartAttribute.setId(attribute.getId());
            ProductOption option=productAttribute.getProductOption();
            ProductOptionValue optionValue=productAttribute.getProductOptionValue();
            List<ProductOptionDescription> optionDescriptions=option.getDescriptionsSettoList();
            List<ProductOptionValueDescription> optionValueDescriptions=optionValue.getDescriptionsSettoList();
            String optName=null;
            String optValue=null;
            if (!CollectionUtils.isEmpty(optionDescriptions) && !CollectionUtils.isEmpty(optionValueDescriptions)) {
              optName=optionDescriptions.get(0).getName();
              optValue=optionValueDescriptions.get(0).getName();
              for (              ProductOptionDescription optionDescription : optionDescriptions) {
                if (optionDescription.getLanguage() != null && optionDescription.getLanguage().getId().intValue() == language.getId().intValue()) {
                  optName=optionDescription.getName();
                  break;
                }
              }
              for (              ProductOptionValueDescription optionValueDescription : optionValueDescriptions) {
                if (optionValueDescription.getLanguage() != null && optionValueDescription.getLanguage().getId().intValue() == language.getId().intValue()) {
                  optValue=optionValueDescription.getName();
                  break;
                }
              }
            }
            if (optName != null) {
              ReadableShoppingCartAttributeOption attributeOption=new ReadableShoppingCartAttributeOption();
              attributeOption.setCode(option.getCode());
              attributeOption.setId(option.getId());
              attributeOption.setName(optName);
              cartAttribute.setOption(attributeOption);
            }
            if (optValue != null) {
              ReadableShoppingCartAttributeOptionValue attributeOptionValue=new ReadableShoppingCartAttributeOptionValue();
              attributeOptionValue.setCode(optionValue.getCode());
              attributeOptionValue.setId(optionValue.getId());
              attributeOptionValue.setName(optValue);
              cartAttribute.setOptionValue(attributeOptionValue);
            }
            shoppingCartItem.getCartItemattributes().add(cartAttribute);
          }
        }
        target.getProducts().add(shoppingCartItem);
      }
    }
    OrderSummary summary=new OrderSummary();
    List<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> productsList=new ArrayList<com.salesmanager.core.model.shoppingcart.ShoppingCartItem>();
    productsList.addAll(source.getLineItems());
    summary.setProducts(productsList);
    OrderTotalSummary orderSummary=shoppingCartCalculationService.calculate(source,store,language);
    if (CollectionUtils.isNotEmpty(orderSummary.getTotals())) {
      List<ReadableOrderTotal> totals=new ArrayList<ReadableOrderTotal>();
      for (      com.salesmanager.core.model.order.OrderTotal t : orderSummary.getTotals()) {
        ReadableOrderTotal total=new ReadableOrderTotal();
        total.setCode(t.getOrderTotalCode());
        total.setValue(t.getValue());
        totals.add(total);
      }
      target.setTotals(totals);
    }
    target.setSubtotal(orderSummary.getSubTotal());
    target.setDisplaySubTotal(pricingService.getDisplayAmount(orderSummary.getSubTotal(),store));
    target.setTotal(orderSummary.getTotal());
    target.setDisplayTotal(pricingService.getDisplayAmount(orderSummary.getTotal(),store));
    target.setQuantity(cartQuantity);
    target.setId(source.getId());
  }
 catch (  Exception e) {
    throw new ConversionException(e);
  }
  return target;
}
