private static void update(TextView badgeText,int count){
  boolean hasBadge=count > 0;
  if (hasBadge) {
    badgeText.setText(String.valueOf(count));
  }
  ViewUtils.setVisibleOrGone(badgeText,hasBadge);
}
