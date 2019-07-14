private void moveScrollToLastMessage(){
  if (chatListView != null && !messages.isEmpty()) {
    chatLayoutManager.scrollToPositionWithOffset(0,0);
  }
}
