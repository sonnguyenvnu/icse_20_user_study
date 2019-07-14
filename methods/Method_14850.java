@Override public void initData(){
  if (StringUtil.isNotEmpty(getIntent().getStringExtra(INTENT_TITLE),false)) {
    tvPasswordSettingTitle.setText("" + StringUtil.getCurrentString());
  }
  if (StringUtil.isNotEmpty(getIntent().getStringExtra(INTENT_REMIND),false)) {
    tvPasswordSettingRemind.setText("" + StringUtil.getCurrentString());
  }
  tvPasswordSettingTitle.setText(isToSetting ? "??????" : "??????");
  if (isToSetting) {
  }
 else {
    if (isToConfirm == false) {
      password=null;
    }
  }
}
