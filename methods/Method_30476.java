public void showAccountList(boolean show){
  if (mShowingAccountList == show) {
    return;
  }
  int headerCount=mNavigationView.getHeaderCount();
  int menuCount=mMenuAdapter.getItemCount() - headerCount;
  if (show) {
    notifyItemRangeRemoved(headerCount,menuCount);
    notifyItemInserted(headerCount);
  }
 else {
    notifyItemRemoved(headerCount);
    notifyItemRangeInserted(headerCount,menuCount);
  }
  mShowingAccountList=show;
}
