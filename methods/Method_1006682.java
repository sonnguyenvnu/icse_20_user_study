@Override public ShoppingCart populate(ShoppingCartData shoppingCart,ShoppingCart cartMdel,final MerchantStore store,Language language){
  try {
    if (shoppingCart.getId() > 0 && StringUtils.isNotBlank(shoppingCart.getCode())) {
      cartMdel=shoppingCartService.getByCode(shoppingCart.getCode(),store);
      if (cartMdel == null) {
        cartMdel=new ShoppingCart();
        cartMdel.setShoppingCartCode(shoppingCart.getCode());
        cartMdel.setMerchantStore(store);
        if (customer != null) {
          cartMdel.setCustomerId(customer.getId());
        }
        shoppingCartService.create(cartMdel);
      }
    }
 else {
      cartMdel.setShoppingCartCode(shoppingCart.getCode());
      cartMdel.setMerchantStore(store);
      if (customer != null) {
        cartMdel.setCustomerId(customer.getId());
      }
      shoppingCartService.create(cartMdel);
    }
    List<ShoppingCartItem> items=shoppingCart.getShoppingCartItems();
    Set<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> newItems=new HashSet<com.salesmanager.core.model.shoppingcart.ShoppingCartItem>();
    if (items != null && items.size() > 0) {
      for (      ShoppingCartItem item : items) {
        Set<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> cartItems=cartMdel.getLineItems();
        if (cartItems != null && cartItems.size() > 0) {
          for (          com.salesmanager.core.model.shoppingcart.ShoppingCartItem dbItem : cartItems) {
            if (dbItem.getId().longValue() == item.getId()) {
              dbItem.setQuantity(item.getQuantity());
              Set<com.salesmanager.core.model.shoppingcart.ShoppingCartAttributeItem> attributes=dbItem.getAttributes();
              Set<com.salesmanager.core.model.shoppingcart.ShoppingCartAttributeItem> newAttributes=new HashSet<com.salesmanager.core.model.shoppingcart.ShoppingCartAttributeItem>();
              List<ShoppingCartAttribute> cartAttributes=item.getShoppingCartAttributes();
              if (!CollectionUtils.isEmpty(cartAttributes)) {
                for (                ShoppingCartAttribute attribute : cartAttributes) {
                  for (                  com.salesmanager.core.model.shoppingcart.ShoppingCartAttributeItem dbAttribute : attributes) {
                    if (dbAttribute.getId().longValue() == attribute.getId()) {
                      newAttributes.add(dbAttribute);
                    }
                  }
                }
                dbItem.setAttributes(newAttributes);
              }
 else {
                dbItem.removeAllAttributes();
              }
              newItems.add(dbItem);
            }
          }
        }
 else {
          com.salesmanager.core.model.shoppingcart.ShoppingCartItem cartItem=createCartItem(cartMdel,item,store);
          Set<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> lineItems=cartMdel.getLineItems();
          if (lineItems == null) {
            lineItems=new HashSet<com.salesmanager.core.model.shoppingcart.ShoppingCartItem>();
            cartMdel.setLineItems(lineItems);
          }
          lineItems.add(cartItem);
          shoppingCartService.update(cartMdel);
        }
      }
    }
  }
 catch (  ServiceException se) {
    LOG.error("Error while converting cart data to cart model.." + se);
    throw new ConversionException("Unable to create cart model",se);
  }
catch (  Exception ex) {
    LOG.error("Error while converting cart data to cart model.." + ex);
    throw new ConversionException("Unable to create cart model",ex);
  }
  return cartMdel;
}
