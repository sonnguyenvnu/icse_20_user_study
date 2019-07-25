/** 
 * ?????????
 * @param product ???????
 * @return ??????
 */
public boolean push(Product product){
  String id=product.getId();
  if (mShoppingList.isEmpty()) {
    mBusinessId=product.getBusinessId();
    ShoppingEntity entity=ShoppingEntity.initWithProduct(product);
    mShoppingList.put(id,entity);
    sendChangeEvent();
    return true;
  }
 else   if (mBusinessId.equals(product.getBusinessId())) {
    ShoppingEntity entity=mShoppingList.containsKey(id) ? mShoppingList.get(id) : null;
    if (entity == null) {
      entity=ShoppingEntity.initWithProduct(product);
    }
 else {
      entity.setQuantity(entity.getQuantity() + 1);
    }
    mShoppingList.put(id,entity);
    sendChangeEvent();
    return true;
  }
  return false;
}
