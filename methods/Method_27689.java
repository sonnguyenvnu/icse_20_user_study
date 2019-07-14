@Override public void showMessage(@StringRes int titleRes,@StringRes int msgRes){
  hideProgress();
  super.showMessage(titleRes,msgRes);
}
