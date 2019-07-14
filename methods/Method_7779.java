public void setChatInfo(TLRPC.ChatFull chatInfo){
  currentAccount=UserConfig.selectedAccount;
  info=chatInfo;
  if (!inlineMediaEnabled && foundContextBot != null && parentFragment != null) {
    TLRPC.Chat chat=parentFragment.getCurrentChat();
    if (chat != null) {
      inlineMediaEnabled=ChatObject.canSendStickers(chat);
      if (inlineMediaEnabled) {
        searchResultUsernames=null;
        notifyDataSetChanged();
        delegate.needChangePanelVisibility(false);
        processFoundUser(foundContextBot);
      }
    }
  }
  if (lastText != null) {
    searchUsernameOrHashtag(lastText,lastPosition,messages,lastUsernameOnly);
  }
}
