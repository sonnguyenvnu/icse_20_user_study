@Override public void onMessageDialogActionClicked(boolean isOk,@Nullable Bundle bundle){
  if (isOk && bundle != null) {
    boolean logout=bundle.getBoolean("logout");
    if (logout) {
      onRequireLogin();
    }
  }
}
