@Override public void onStickerSelected(TLRPC.Document sticker,Object parent,boolean clearsInputField){
  if (searchingType != 0) {
    searchingType=0;
    emojiView.closeSearch(true);
    emojiView.hideSearchKeyboard();
  }
  setStickersExpanded(false,true,false);
  SendMessagesHelper.getInstance(currentAccount).sendSticker(sticker,dialog_id,replyingMessageObject,parent);
  if (delegate != null) {
    delegate.onMessageSend(null);
  }
  if (clearsInputField) {
    setFieldText("");
  }
  DataQuery.getInstance(currentAccount).addRecentSticker(DataQuery.TYPE_IMAGE,parent,sticker,(int)(System.currentTimeMillis() / 1000),false);
}
