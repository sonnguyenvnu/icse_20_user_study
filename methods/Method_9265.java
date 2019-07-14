private void resolveStickerSet(){
  if (listAdapter == null) {
    return;
  }
  if (reqId != 0) {
    ConnectionsManager.getInstance(currentAccount).cancelRequest(reqId,true);
    reqId=0;
  }
  if (queryRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(queryRunnable);
    queryRunnable=null;
  }
  selectedStickerSet=null;
  if (usernameTextView.length() <= 0) {
    searching=false;
    searchWas=false;
    if (selectedStickerRow != -1) {
      updateRows();
    }
    return;
  }
  searching=true;
  searchWas=true;
  final String query=usernameTextView.getText().toString();
  TLRPC.TL_messages_stickerSet existingSet=DataQuery.getInstance(currentAccount).getStickerSetByName(query);
  if (existingSet != null) {
    selectedStickerSet=existingSet;
  }
  if (selectedStickerRow == -1) {
    updateRows();
  }
 else {
    listAdapter.notifyItemChanged(selectedStickerRow);
  }
  if (existingSet != null) {
    searching=false;
    return;
  }
  AndroidUtilities.runOnUIThread(queryRunnable=() -> {
    if (queryRunnable == null) {
      return;
    }
    TLRPC.TL_messages_getStickerSet req=new TLRPC.TL_messages_getStickerSet();
    req.stickerset=new TLRPC.TL_inputStickerSetShortName();
    req.stickerset.short_name=query;
    reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      searching=false;
      if (response instanceof TLRPC.TL_messages_stickerSet) {
        selectedStickerSet=(TLRPC.TL_messages_stickerSet)response;
        if (donePressed) {
          saveStickerSet();
        }
 else {
          if (selectedStickerRow != -1) {
            listAdapter.notifyItemChanged(selectedStickerRow);
          }
 else {
            updateRows();
          }
        }
      }
 else {
        if (selectedStickerRow != -1) {
          listAdapter.notifyItemChanged(selectedStickerRow);
        }
        if (donePressed) {
          donePressed=false;
          showEditDoneProgress(false);
          if (getParentActivity() != null) {
            Toast.makeText(getParentActivity(),LocaleController.getString("AddStickersNotFound",R.string.AddStickersNotFound),Toast.LENGTH_SHORT).show();
          }
        }
      }
      reqId=0;
    }
));
  }
,500);
}
