private void showTotalPrice(){
  if (mModelShopCart != null && mModelShopCart.getShoppingTotalPrice() > 0) {
    totalPriceTextView.setVisibility(View.VISIBLE);
    totalPriceTextView.setText("¥ " + mModelShopCart.getShoppingTotalPrice());
    totalPriceNumTextView.setVisibility(View.VISIBLE);
    totalPriceNumTextView.setText("" + mModelShopCart.getShoppingAccount());
  }
 else {
    totalPriceTextView.setVisibility(View.GONE);
    totalPriceNumTextView.setVisibility(View.GONE);
  }
}
