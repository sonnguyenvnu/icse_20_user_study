/** 
 * ????
 * @param shoppingEntities
 */
public void again(List<ShoppingEntity> shoppingEntities){
  mShoppingList.clear();
  for (  ShoppingEntity entity : shoppingEntities) {
    Product product=entity.getProduct();
    if (product != null) {
      mBusinessId=product.getBusinessId();
      mShoppingList.put(product.getId(),entity);
    }
  }
  sendChangeEvent();
}
