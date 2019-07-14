public void checkChannelRights(){
  if (parentFragment == null) {
    return;
  }
  TLRPC.Chat chat=parentFragment.getCurrentChat();
  if (chat != null) {
    audioVideoButtonContainer.setAlpha(ChatObject.canSendMedia(chat) ? 1.0f : 0.5f);
    if (emojiView != null) {
      emojiView.setStickersBanned(!ChatObject.canSendStickers(chat),chat.id);
    }
  }
}
