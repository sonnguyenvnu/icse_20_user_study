public void bind(ShoppingEntity entity){
  mNameTxt.setText(entity.getName());
  mPriceTxt.setText(StringFetcher.getString(R.string.label_price,entity.getTotalPrice()));
  final Product finalProduct=entity.getProduct();
  int quantity=ShoppingCart.getInstance().getQuantityForProduct(finalProduct);
  mShoppingCountView.setShoppingCount(quantity);
  mShoppingCountView.setOnShoppingClickListener(new ShoppingCountView.ShoppingClickListener(){
    @Override public void onAddClick(    int num){
      if (!ShoppingCart.getInstance().push(finalProduct)) {
        int oldQuantity=ShoppingCart.getInstance().getQuantityForProduct(finalProduct);
        mShoppingCountView.setShoppingCount(oldQuantity);
      }
    }
    @Override public void onMinusClick(    int num){
      if (!ShoppingCart.getInstance().pop(finalProduct)) {
        int oldQuantity=ShoppingCart.getInstance().getQuantityForProduct(finalProduct);
        mShoppingCountView.setShoppingCount(oldQuantity);
      }
    }
  }
);
}
