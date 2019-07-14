private void showOrHideAndroidPayConfirmation(final boolean visible){
  if (visible) {
    this.webView.setVisibility(View.GONE);
    this.confirmationGroup.setVisibility(View.VISIBLE);
  }
 else {
    this.webView.setVisibility(View.VISIBLE);
    this.confirmationGroup.setVisibility(View.GONE);
  }
}
