public void setChatInfo(TLRPC.ChatFull chatInfo){
  info=chatInfo;
  if (info != null && info.migrated_from_chat_id != 0 && mergeDialogId == 0) {
    mergeDialogId=-info.migrated_from_chat_id;
    for (int a=0; a < sharedMediaData.length; a++) {
      sharedMediaData[a].max_id[1]=info.migrated_from_max_id;
      sharedMediaData[a].endReached[1]=false;
    }
  }
}
