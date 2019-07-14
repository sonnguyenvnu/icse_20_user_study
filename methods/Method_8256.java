private void removeSelectedMessageHighlight(){
  if (unselectRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(unselectRunnable);
    unselectRunnable=null;
  }
  highlightMessageId=Integer.MAX_VALUE;
}
