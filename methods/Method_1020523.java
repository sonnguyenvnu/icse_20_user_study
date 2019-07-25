public void bind(CartInfo.DiscountInfo item){
  iconTxt.setText(item.getIconName());
  iconTxt.setBackgroundColor(Color.parseColor(item.getIconColor()));
  nameTxt.setText(item.getName());
  priceTxt.setText(StringFetcher.getString(R.string.label_discount_price,item.getPrice()));
  if (!TextUtils.isEmpty(item.getDescription())) {
    descTxt.setText(item.getDescription());
    descTxt.setVisibility(View.VISIBLE);
  }
 else {
    descTxt.setVisibility(View.GONE);
  }
}
