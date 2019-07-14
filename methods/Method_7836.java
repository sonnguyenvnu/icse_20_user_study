protected void cancelCheckLongPress(){
  checkingForLongPress=false;
  if (pendingCheckForLongPress != null) {
    windowView.removeCallbacks(pendingCheckForLongPress);
    pendingCheckForLongPress=null;
  }
  if (pendingCheckForTap != null) {
    windowView.removeCallbacks(pendingCheckForTap);
    pendingCheckForTap=null;
  }
}
