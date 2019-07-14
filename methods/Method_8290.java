private void checkListViewPaddingsInternal(){
  if (chatLayoutManager == null) {
    return;
  }
  try {
    int firstVisPos=chatLayoutManager.findFirstVisibleItemPosition();
    int lastVisPos=RecyclerView.NO_POSITION;
    if (!wasManualScroll && unreadMessageObject != null) {
      int pos=messages.indexOf(unreadMessageObject);
      if (pos >= 0) {
        lastVisPos=pos + chatAdapter.messagesStartRow;
        firstVisPos=RecyclerView.NO_POSITION;
      }
    }
    int top=0;
    if (firstVisPos != RecyclerView.NO_POSITION) {
      View firstVisView=chatLayoutManager.findViewByPosition(firstVisPos);
      top=((firstVisView == null) ? 0 : chatListView.getMeasuredHeight() - firstVisView.getBottom() - chatListView.getPaddingBottom());
    }
    if (chatListView.getPaddingTop() != AndroidUtilities.dp(52) && (pinnedMessageView != null && pinnedMessageView.getTag() == null || reportSpamView != null && reportSpamView.getTag() == null)) {
      chatListView.setPadding(0,AndroidUtilities.dp(52),0,AndroidUtilities.dp(3));
      FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)floatingDateView.getLayoutParams();
      layoutParams.topMargin=AndroidUtilities.dp(52);
      floatingDateView.setLayoutParams(layoutParams);
      chatListView.setTopGlowOffset(AndroidUtilities.dp(48));
    }
 else     if (chatListView.getPaddingTop() != AndroidUtilities.dp(4) && (pinnedMessageView == null || pinnedMessageView.getTag() != null) && (reportSpamView == null || reportSpamView.getTag() != null)) {
      chatListView.setPadding(0,AndroidUtilities.dp(4),0,AndroidUtilities.dp(3));
      FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)floatingDateView.getLayoutParams();
      layoutParams.topMargin=AndroidUtilities.dp(4);
      floatingDateView.setLayoutParams(layoutParams);
      chatListView.setTopGlowOffset(0);
    }
 else {
      firstVisPos=RecyclerView.NO_POSITION;
    }
    if (firstVisPos != RecyclerView.NO_POSITION) {
      chatLayoutManager.scrollToPositionWithOffset(firstVisPos,top);
    }
 else     if (lastVisPos != RecyclerView.NO_POSITION) {
      top=chatListView.getMeasuredHeight() - chatListView.getPaddingBottom() - chatListView.getPaddingTop() - AndroidUtilities.dp(29);
      chatLayoutManager.scrollToPositionWithOffset(lastVisPos,top);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
