@Override public void showProgress(@StringRes int resId){
  login.hide();
  AppHelper.hideKeyboard(login);
  AnimHelper.animateVisibility(progress,true);
}
