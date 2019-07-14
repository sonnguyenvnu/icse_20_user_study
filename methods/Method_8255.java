private void startMessageUnselect(){
  if (unselectRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(unselectRunnable);
  }
  unselectRunnable=() -> {
    highlightMessageId=Integer.MAX_VALUE;
    updateVisibleRows();
    unselectRunnable=null;
  }
;
  AndroidUtilities.runOnUIThread(unselectRunnable,1000);
}
