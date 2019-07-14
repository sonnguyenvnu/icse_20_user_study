@Override public void onHandleConfirmDialog(@Nullable Bundle bundle){
  if (bundle != null) {
    boolean proceedCloseIssue=bundle.getBoolean(BundleConstant.EXTRA);
    boolean proceedLockUnlock=bundle.getBoolean(BundleConstant.EXTRA_TWO);
    if (proceedCloseIssue) {
      onOpenCloseIssue();
    }
 else     if (proceedLockUnlock) {
      onLockUnlockIssue(null);
    }
  }
}
