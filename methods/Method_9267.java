private void saveStickerSet(){
  if (info == null || info.stickerset != null && selectedStickerSet != null && selectedStickerSet.set.id == info.stickerset.id || info.stickerset == null && selectedStickerSet == null) {
    finishFragment();
    return;
  }
  showEditDoneProgress(true);
  TLRPC.TL_channels_setStickers req=new TLRPC.TL_channels_setStickers();
  req.channel=MessagesController.getInstance(currentAccount).getInputChannel(chatId);
  if (selectedStickerSet == null) {
    req.stickerset=new TLRPC.TL_inputStickerSetEmpty();
  }
 else {
    MessagesController.getEmojiSettings(currentAccount).edit().remove("group_hide_stickers_" + info.id).commit();
    req.stickerset=new TLRPC.TL_inputStickerSetID();
    req.stickerset.id=selectedStickerSet.set.id;
    req.stickerset.access_hash=selectedStickerSet.set.access_hash;
  }
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (error == null) {
      if (selectedStickerSet == null) {
        info.stickerset=null;
      }
 else {
        info.stickerset=selectedStickerSet.set;
        DataQuery.getInstance(currentAccount).putGroupStickerSet(selectedStickerSet);
      }
      if (info.stickerset == null) {
        info.flags|=256;
      }
 else {
        info.flags=info.flags & ~256;
      }
      MessagesStorage.getInstance(currentAccount).updateChatInfo(info,false);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatInfoDidLoad,info,0,true,null);
      finishFragment();
    }
 else {
      Toast.makeText(getParentActivity(),LocaleController.getString("ErrorOccurred",R.string.ErrorOccurred) + "\n" + error.text,Toast.LENGTH_SHORT).show();
      donePressed=false;
      showEditDoneProgress(false);
    }
  }
));
}
