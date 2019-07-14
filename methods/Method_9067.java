private void loadStickerSet(){
  if (inputStickerSet != null) {
    if (stickerSet == null && inputStickerSet.short_name != null) {
      stickerSet=DataQuery.getInstance(currentAccount).getStickerSetByName(inputStickerSet.short_name);
    }
    if (stickerSet == null) {
      stickerSet=DataQuery.getInstance(currentAccount).getStickerSetById(inputStickerSet.id);
    }
    if (stickerSet == null) {
      TLRPC.TL_messages_getStickerSet req=new TLRPC.TL_messages_getStickerSet();
      req.stickerset=inputStickerSet;
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
        reqId=0;
        if (error == null) {
          optionsButton.setVisibility(View.VISIBLE);
          stickerSet=(TLRPC.TL_messages_stickerSet)response;
          showEmoji=!stickerSet.set.masks;
          updateSendButton();
          updateFields();
          adapter.notifyDataSetChanged();
        }
 else {
          Toast.makeText(getContext(),LocaleController.getString("AddStickersNotFound",R.string.AddStickersNotFound),Toast.LENGTH_SHORT).show();
          dismiss();
        }
      }
));
    }
 else     if (adapter != null) {
      updateSendButton();
      updateFields();
      adapter.notifyDataSetChanged();
    }
  }
  if (stickerSet != null) {
    showEmoji=!stickerSet.set.masks;
  }
}
