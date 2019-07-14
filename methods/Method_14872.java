@Override public void onDragBottom(boolean rightToLeft){
  if (rightToLeft) {
    SettingUtil.restoreDefault();
    initData();
    return;
  }
  finish();
}
