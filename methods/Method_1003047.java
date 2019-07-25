@Override public int update(){
  Index index=null;
  if (sortedInsertMode) {
    if (!session.getDatabase().isMVStore()) {
      table.lock(session,true,true);
    }
    index=table.getScanIndex(session);
    index.setSortedInsertMode(true);
  }
  try {
    return insertRows();
  }
  finally {
    if (index != null) {
      index.setSortedInsertMode(false);
    }
  }
}
