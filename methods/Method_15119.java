@Override public void initEvent(){
  if (ivBaseTabReturn != null) {
    ivBaseTabReturn.setOnClickListener(this);
  }
  if (tvBaseTabReturn != null) {
    tvBaseTabReturn.setOnClickListener(this);
  }
  if (tvBaseTabForward != null) {
    tvBaseTabForward.setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View v){
        onForwardClick(v);
      }
    }
);
  }
  topTabView.setOnTabSelectedListener(this);
}
