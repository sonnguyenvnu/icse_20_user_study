@Override public void remove(Session session,Row row){
  if (rowCount == 1) {
    rows=Utils.newSmallArrayList();
    firstFree=-1;
  }
 else {
    Row free=session.createRow(null,1);
    free.setKey(firstFree);
    long key=row.getKey();
    if (rows.size() <= key) {
      throw DbException.get(ErrorCode.ROW_NOT_FOUND_WHEN_DELETING_1,rows.size() + ": " + key);
    }
    rows.set((int)key,free);
    firstFree=key;
  }
  rowCount--;
}
