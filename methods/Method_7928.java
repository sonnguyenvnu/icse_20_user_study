protected void cancelCheckLongPress(){
  checkingForLongPress=false;
  if (pendingCheckForLongPress != null) {
    removeCallbacks(pendingCheckForLongPress);
  }
  if (pendingCheckForTap != null) {
    removeCallbacks(pendingCheckForTap);
  }
}
