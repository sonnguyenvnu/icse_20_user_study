private void addFragments(){
  mMainFragment=HomeFragment.newInstance();
  FragmentUtils.add(mMainFragment,this,R.id.container);
  mNotificationListFragment=NotificationListFragment.newInstance();
  FragmentUtils.add(mNotificationListFragment,this,R.id.notification_list_drawer);
  mDoumailUnreadCountFragment=DoumailUnreadCountFragment.newInstance();
  FragmentUtils.add(mDoumailUnreadCountFragment,this,FRAGMENT_TAG_DOUMAIL_UNREAD_COUNT);
}
