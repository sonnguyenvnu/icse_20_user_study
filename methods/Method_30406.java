public void onDoumailUnreadCountUpdate(int count){
  if (mDoumailMenuItem != null) {
    ActionItemBadge.update(mDoumailMenuItem,count);
  }
}
