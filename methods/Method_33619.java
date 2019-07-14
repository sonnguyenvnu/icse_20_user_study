private boolean isOtherType(String selectType){
  String clickText=SPUtils.getString(GANK_CALA,"??");
  if (clickText.equals(selectType)) {
    ToastUtil.showToast("?????" + selectType + "??");
    return false;
  }
 else {
    return true;
  }
}
