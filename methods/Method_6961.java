private void saveDiffParamsInternal(final int seq,final int pts,final int date,final int qts){
  try {
    if (lastSavedSeq == seq && lastSavedPts == pts && lastSavedDate == date && lastQtsValue == qts) {
      return;
    }
    SQLitePreparedStatement state=database.executeFast("UPDATE params SET seq = ?, pts = ?, date = ?, qts = ? WHERE id = 1");
    state.bindInteger(1,seq);
    state.bindInteger(2,pts);
    state.bindInteger(3,date);
    state.bindInteger(4,qts);
    state.step();
    state.dispose();
    lastSavedSeq=seq;
    lastSavedPts=pts;
    lastSavedDate=date;
    lastSavedQts=qts;
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
