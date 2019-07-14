@Override public void onShowCommitCount(long sum){
  if (tabsBadgeListener != null) {
    tabsBadgeListener.onSetBadge(2,(int)sum);
  }
}
