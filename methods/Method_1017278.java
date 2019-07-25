public void buy(String product,int quantity){
  if (cart.containsKey(product)) {
    int currentQuantity=cart.get(product);
    currentQuantity+=quantity;
    cart.put(product,currentQuantity);
  }
 else {
    cart.put(product,quantity);
  }
}
