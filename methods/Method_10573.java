private void showTotalPrice(){
  if (mModelShopCart != null && mModelShopCart.getShoppingTotalPrice() > 0) {
    totalPriceTextView.setVisibility(View.VISIBLE);
    totalPriceTextView.setText(getContext().getResources().getString(R.string.rmb) + " " + mModelShopCart.getShoppingTotalPrice());
    totalPriceNumTextView.setVisibility(View.VISIBLE);
    totalPriceNumTextView.setText("" + mModelShopCart.getShoppingAccount());
  }
 else {
    totalPriceTextView.setVisibility(View.GONE);
    totalPriceNumTextView.setVisibility(View.GONE);
  }
}
