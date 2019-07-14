private void checkListViewPaddings(){
  if (!wasManualScroll && unreadMessageObject != null) {
    int pos=messages.indexOf(unreadMessageObject);
    if (pos >= 0) {
      fixPaddingsInLayout=true;
      if (fragmentView != null) {
        fragmentView.requestLayout();
      }
    }
  }
 else {
    AndroidUtilities.runOnUIThread(this::checkListViewPaddingsInternal);
  }
}
