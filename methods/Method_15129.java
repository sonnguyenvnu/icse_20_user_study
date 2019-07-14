@Override public void initEvent(){
  if (ivBaseTabReturn != null) {
    ivBaseTabReturn.setOnClickListener(this);
  }
  if (tvBaseTabReturn != null) {
    tvBaseTabReturn.setOnClickListener(this);
  }
  topTabView.setOnTabSelectedListener(this);
}
