@Override public void onSetCount(int totalCount){
  if (tabsBadgeListener != null) {
switch (getIssuesType()) {
case CREATED:
      tabsBadgeListener.onSetBadge(0,totalCount);
    break;
case ASSIGNED:
  tabsBadgeListener.onSetBadge(1,totalCount);
break;
case MENTIONED:
tabsBadgeListener.onSetBadge(2,totalCount);
break;
case PARTICIPATED:
tabsBadgeListener.onSetBadge(3,totalCount);
break;
}
}
}
