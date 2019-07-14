public void setAccountsShowed(boolean value,boolean animated){
  if (accountsShowed == value) {
    return;
  }
  accountsShowed=value;
  if (profileCell != null) {
    profileCell.setAccountsShowed(accountsShowed);
  }
  MessagesController.getGlobalMainSettings().edit().putBoolean("accountsShowed",accountsShowed).commit();
  if (animated) {
    if (accountsShowed) {
      notifyItemRangeInserted(2,getAccountRowsCount());
    }
 else {
      notifyItemRangeRemoved(2,getAccountRowsCount());
    }
  }
 else {
    notifyDataSetChanged();
  }
}
