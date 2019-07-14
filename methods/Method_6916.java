private int isValidUpdate(TLRPC.Updates updates,int type){
  if (type == 0) {
    int seq=getUpdateSeq(updates);
    if (MessagesStorage.getInstance(currentAccount).getLastSeqValue() + 1 == seq || MessagesStorage.getInstance(currentAccount).getLastSeqValue() == seq) {
      return 0;
    }
 else     if (MessagesStorage.getInstance(currentAccount).getLastSeqValue() < seq) {
      return 1;
    }
 else {
      return 2;
    }
  }
 else   if (type == 1) {
    if (updates.pts <= MessagesStorage.getInstance(currentAccount).getLastPtsValue()) {
      return 2;
    }
 else     if (MessagesStorage.getInstance(currentAccount).getLastPtsValue() + updates.pts_count == updates.pts) {
      return 0;
    }
 else {
      return 1;
    }
  }
 else   if (type == 2) {
    if (updates.pts <= MessagesStorage.getInstance(currentAccount).getLastQtsValue()) {
      return 2;
    }
 else     if (MessagesStorage.getInstance(currentAccount).getLastQtsValue() + updates.updates.size() == updates.pts) {
      return 0;
    }
 else {
      return 1;
    }
  }
  return 0;
}
