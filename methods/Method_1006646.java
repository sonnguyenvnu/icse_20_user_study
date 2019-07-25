/** 
 * <p> Method used to recalculate state of shopping cart every time any change has been made to underlying  {@link ShoppingCart} object in DB.</p> Following operations will be performed by this method. <li>Calculate price for each  {@link ShoppingCartItem} and update it.</li><p> This method is backbone method for all price calculation related to shopping cart. </p>
 * @see OrderServiceImpl
 * @param cartModel
 * @param customer
 * @param store
 * @param language
 * @throws ServiceException
 */
@Override public OrderTotalSummary calculate(final ShoppingCart cartModel,final Customer customer,final MerchantStore store,final Language language) throws ServiceException {
  Validate.notNull(cartModel,"cart cannot be null");
  Validate.notNull(cartModel.getLineItems(),"Cart should have line items.");
  Validate.notNull(store,"MerchantStore cannot be null");
  Validate.notNull(customer,"Customer cannot be null");
  OrderTotalSummary orderTotalSummary=orderService.calculateShoppingCartTotal(cartModel,customer,store,language);
  updateCartModel(cartModel);
  return orderTotalSummary;
}
