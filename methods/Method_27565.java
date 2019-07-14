public boolean isProfile(){
  return !InputHelper.isEmpty(getArguments().getString(BundleConstant.EXTRA)) && !getArguments().getBoolean(BundleConstant.EXTRA_TWO);
}
