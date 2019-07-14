protected void startCheckLongPress(){
  if (checkingForLongPress) {
    return;
  }
  checkingForLongPress=true;
  if (pendingCheckForTap == null) {
    pendingCheckForTap=new CheckForTap();
  }
  postDelayed(pendingCheckForTap,ViewConfiguration.getTapTimeout());
}
