private void searchServerStickers(final String emoji,final String originalEmoji){
  TLRPC.TL_messages_getStickers req=new TLRPC.TL_messages_getStickers();
  req.emoticon=originalEmoji;
  req.hash=0;
  lastReqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    lastReqId=0;
    if (!emoji.equals(lastSticker) || !(response instanceof TLRPC.TL_messages_stickers)) {
      return;
    }
    delayLocalResults=false;
    TLRPC.TL_messages_stickers res=(TLRPC.TL_messages_stickers)response;
    int oldCount=stickers != null ? stickers.size() : 0;
    addStickersToResult(res.stickers,"sticker_search_" + emoji);
    int newCount=stickers != null ? stickers.size() : 0;
    if (!visible && stickers != null && !stickers.isEmpty()) {
      checkStickerFilesExistAndDownload();
      boolean show=stickers != null && !stickers.isEmpty() && stickersToLoad.isEmpty();
      if (show) {
        keywordResults=null;
      }
      delegate.needChangePanelVisibility(show);
      visible=true;
    }
    if (oldCount != newCount) {
      notifyDataSetChanged();
    }
  }
));
}
