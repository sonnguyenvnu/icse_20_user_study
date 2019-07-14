@Override public void onDragBottom(boolean rightToLeft){
  if (rightToLeft) {
    etServerSettingNormal.setText(StringUtil.getTrimedString(SettingUtil.URL_SERVER_ADDRESS_NORMAL_HTTP));
    etServerSettingTest.setText(StringUtil.getTrimedString(SettingUtil.URL_SERVER_ADDRESS_TEST));
    return;
  }
  finish();
}
