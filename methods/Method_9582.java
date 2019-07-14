private void loadMediaCounts(){
  if (dialog_id != 0) {
    DataQuery.getInstance(currentAccount).getMediaCounts(dialog_id,classGuid);
  }
 else   if (user_id != 0) {
    DataQuery.getInstance(currentAccount).getMediaCounts(user_id,classGuid);
  }
 else   if (chat_id > 0) {
    DataQuery.getInstance(currentAccount).getMediaCounts(-chat_id,classGuid);
    if (mergeDialogId != 0) {
      DataQuery.getInstance(currentAccount).getMediaCounts(mergeDialogId,classGuid);
    }
  }
}
