private void notifyAccountListViewChanged(){
  if (mShowingAccountList) {
    notifyItemChanged(mNavigationView.getHeaderCount());
  }
}
