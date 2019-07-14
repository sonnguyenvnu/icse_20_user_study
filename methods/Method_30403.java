@Override public void reloadForActiveAccountChange(){
  if (mMainFragment != null) {
    FragmentUtils.remove(mMainFragment);
  }
  if (mNotificationListFragment != null) {
    FragmentUtils.remove(mNotificationListFragment);
  }
  if (mDoumailUnreadCountFragment != null) {
    FragmentUtils.remove(mDoumailUnreadCountFragment);
  }
  FragmentUtils.executePendingTransactions(this);
  addFragments();
  FragmentUtils.executePendingTransactions(this);
}
