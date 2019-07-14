public void addStickerToRecent(TLRPC.Document sticker){
  createEmojiView();
  emojiView.addRecentSticker(sticker);
}
