@Override public void initData(){
  phone=DataManager.getInstance().getLastUserPhone();
  if (StringUtil.isPhone(phone)) {
    etLoginPhone.setText("" + phone);
    etLoginPassword.requestFocus();
  }
}
