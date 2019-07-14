@Override public void onMessageDialogActionClicked(boolean isOk,@Nullable Bundle bundle){
  super.onMessageDialogActionClicked(isOk,bundle);
  if (bundle != null) {
    boolean isDelete=bundle.getBoolean(BundleConstant.EXTRA) && isOk;
    if (isDelete) {
      getPresenter().onDeleteGist();
    }
  }
}
