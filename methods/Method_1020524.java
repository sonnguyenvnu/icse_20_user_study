public void bind(CartInfo.ExtraFee item){
  nameTxt.setText(item.getName());
  priceTxt.setText(StringFetcher.getString(R.string.label_price,item.getPrice()));
  if (!TextUtils.isEmpty(item.getDescription())) {
    descTxt.setText(item.getDescription());
    descTxt.setVisibility(View.VISIBLE);
  }
 else {
    descTxt.setVisibility(View.GONE);
  }
}
