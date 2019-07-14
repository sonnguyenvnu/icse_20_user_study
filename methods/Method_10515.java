@OnClick(R.id.btn_get_phone_info) public void onViewClicked(){
  mLlInfoRoot.setVisibility(View.VISIBLE);
  getPhoneInfo();
  mBtnGetPhoneInfo.setVisibility(View.GONE);
}
