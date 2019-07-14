private void updateRows(){
  currentChat=MessagesController.getInstance(currentAccount).getChat(currentChatId);
  if (currentChat == null) {
    return;
  }
  rowCount=0;
  helpRow=-1;
  createChatRow=-1;
  chatStartRow=-1;
  chatEndRow=-1;
  removeChatRow=-1;
  detailRow=-1;
  helpRow=rowCount++;
  if (isChannel) {
    if (info.linked_chat_id == 0) {
      createChatRow=rowCount++;
    }
    chatStartRow=rowCount;
    rowCount+=chats.size();
    chatEndRow=rowCount;
    if (info.linked_chat_id != 0) {
      createChatRow=rowCount++;
    }
    detailRow=rowCount++;
  }
 else {
    chatStartRow=rowCount;
    rowCount+=chats.size();
    chatEndRow=rowCount;
    createChatRow=rowCount++;
    detailRow=rowCount++;
  }
  if (listViewAdapter != null) {
    listViewAdapter.notifyDataSetChanged();
  }
  if (searchItem != null) {
    searchItem.setVisibility(chats.size() > 10 ? View.VISIBLE : View.GONE);
  }
}
