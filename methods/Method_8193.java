private void moveScrollToLastMessage(){
  if (chatListView != null && !messages.isEmpty()) {
    chatLayoutManager.scrollToPositionWithOffset(messages.size() - 1,-100000 - chatListView.getPaddingTop());
  }
}
