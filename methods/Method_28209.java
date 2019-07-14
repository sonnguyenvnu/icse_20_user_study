@Override public void onUpdateCount(int totalCount){
  if (tabsBadgeListener != null)   tabsBadgeListener.onSetBadge(0,totalCount);
}
