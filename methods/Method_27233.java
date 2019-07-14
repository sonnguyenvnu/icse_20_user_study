public static boolean isAutoImageDisabled(){
  return PrefHelper.getBoolean(DISABLE_AUTO_LOAD_IMAGE) && AppHelper.isDataPlan();
}
