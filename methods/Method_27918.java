@Override public void onUpdateCount(int starredCount){
  if (tabsBadgeListener != null) {
    tabsBadgeListener.onSetBadge(3,starredCount);
  }
}
