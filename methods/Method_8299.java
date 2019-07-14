private boolean fixLayoutInternal(){
  if (!AndroidUtilities.isTablet() && ApplicationLoader.applicationContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
    selectedMessagesCountTextView.setTextSize(18);
  }
 else {
    selectedMessagesCountTextView.setTextSize(20);
  }
  HashMap<Long,MessageObject.GroupedMessages> newGroups=null;
  int count=chatListView.getChildCount();
  for (int a=0; a < count; a++) {
    View child=chatListView.getChildAt(a);
    if (child instanceof ChatMessageCell) {
      MessageObject.GroupedMessages groupedMessages=((ChatMessageCell)child).getCurrentMessagesGroup();
      if (groupedMessages != null && groupedMessages.hasSibling) {
        if (newGroups == null) {
          newGroups=new HashMap<>();
        }
        if (!newGroups.containsKey(groupedMessages.groupId)) {
          newGroups.put(groupedMessages.groupId,groupedMessages);
          MessageObject messageObject=groupedMessages.messages.get(groupedMessages.messages.size() - 1);
          int idx=messages.indexOf(messageObject);
          if (idx >= 0) {
            chatAdapter.notifyItemRangeChanged(idx + chatAdapter.messagesStartRow,groupedMessages.messages.size());
          }
        }
      }
    }
  }
  if (AndroidUtilities.isTablet()) {
    if (AndroidUtilities.isSmallTablet() && ApplicationLoader.applicationContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
      actionBar.setBackButtonDrawable(new BackDrawable(false));
      if (fragmentContextView != null && fragmentContextView.getParent() == null) {
        ((ViewGroup)fragmentView).addView(fragmentContextView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,39,Gravity.TOP | Gravity.LEFT,0,-36,0,0));
      }
    }
 else {
      actionBar.setBackButtonDrawable(new BackDrawable(parentLayout == null || parentLayout.fragmentsStack.isEmpty() || parentLayout.fragmentsStack.get(0) == ChatActivity.this || parentLayout.fragmentsStack.size() == 1));
      if (fragmentContextView != null && fragmentContextView.getParent() != null) {
        fragmentView.setPadding(0,0,0,0);
        ((ViewGroup)fragmentView).removeView(fragmentContextView);
      }
    }
    return false;
  }
  return true;
}
