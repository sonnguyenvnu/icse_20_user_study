@Override protected void onDialogDismiss(Dialog dialog){
  if (listView != null) {
    listView.invalidateViews();
  }
}
