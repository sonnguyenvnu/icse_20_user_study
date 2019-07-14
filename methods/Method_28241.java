@Override public void onMessageDialogActionClicked(boolean isOk,@Nullable Bundle bundle){
  super.onMessageDialogActionClicked(isOk,bundle);
  if (isOk) {
    if (bundle != null) {
      if (bundle.getBoolean(BundleConstant.EXTRA_TYPE)) {
        hideAndClearReviews();
        return;
      }
    }
    getPresenter().onHandleConfirmDialog(bundle);
  }
}
