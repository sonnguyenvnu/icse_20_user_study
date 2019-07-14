@Override public void onMessageDialogActionClicked(boolean isOk,@Nullable Bundle bundle){
  super.onMessageDialogActionClicked(isOk,bundle);
  if (bundle != null && isOk) {
    long id=bundle.getLong(BundleConstant.ID);
    int position=bundle.getInt(BundleConstant.EXTRA);
    PinnedIssues.delete(id);
    adapter.removeItem(position);
  }
}
