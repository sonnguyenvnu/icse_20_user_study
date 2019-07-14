public void setAccountsShowed(boolean value){
  if (accountsShowed == value) {
    return;
  }
  accountsShowed=value;
  arrowView.setImageResource(accountsShowed ? R.drawable.collapse_up : R.drawable.collapse_down);
}
