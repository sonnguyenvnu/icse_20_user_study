public void bind(Business business){
  mPhotoImg.loadBusinessPhoto(business);
  mNameTxt.setText(business.getName());
  mMonthSalesTxt.setText(StringFetcher.getString(R.string.label_month_sales,business.getMonthSales()));
  mMultiContentTxt.setText(StringFetcher.getString(R.string.label_business_multi_content,String.valueOf(business.getMinPrice()),String.valueOf(business.getShippingFee()),String.valueOf(business.getShippingTime())));
  ShoppingCart shoppingCart=ShoppingCart.getInstance();
  int count=shoppingCart.getTotalQuantity();
  if (business.getId().equals(shoppingCart.getBusinessId()) && count > 0) {
    mBadgeView.showTextBadge(String.valueOf(count));
  }
 else {
    mBadgeView.hiddenBadge();
  }
}
