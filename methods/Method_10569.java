public boolean addShoppingSingle(ModelDish modelDish){
  int remain=modelDish.getDishRemain();
  if (remain <= 0)   return false;
  modelDish.setDishRemain(--remain);
  int num=0;
  if (shoppingSingle.containsKey(modelDish)) {
    num=shoppingSingle.get(modelDish);
  }
  num+=1;
  shoppingSingle.put(modelDish,num);
  Log.e("TAG","addShoppingSingle: " + shoppingSingle.get(modelDish));
  shoppingTotalPrice+=modelDish.getDishPrice();
  shoppingAccount++;
  return true;
}
