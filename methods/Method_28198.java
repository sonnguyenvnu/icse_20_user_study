@Override public void onUpdateCount(int totalCount){
  if (tabsBadgeListener != null)   tabsBadgeListener.onSetBadge(1,totalCount);
}
