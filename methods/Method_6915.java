private int getUpdateSeq(TLRPC.Updates updates){
  if (updates instanceof TLRPC.TL_updatesCombined) {
    return updates.seq_start;
  }
 else {
    return updates.seq;
  }
}
