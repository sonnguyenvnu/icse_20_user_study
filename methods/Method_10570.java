public boolean subShoppingSingle(ModelDish modelDish){
  int num=0;
  if (shoppingSingle.containsKey(modelDish)) {
    num=shoppingSingle.get(modelDish);
  }
  if (num <= 0)   return false;
  num--;
  int remain=modelDish.getDishRemain();
  modelDish.setDishRemain(++remain);
  shoppingSingle.put(modelDish,num);
  if (num == 0)   shoppingSingle.remove(modelDish);
  shoppingTotalPrice-=modelDish.getDishPrice();
  shoppingAccount--;
  return true;
}
