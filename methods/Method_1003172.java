@Override public void truncate(Session session){
  lastModificationId=database.getNextModificationDataId();
  for (int i=indexes.size() - 1; i >= 0; i--) {
    Index index=indexes.get(i);
    index.truncate(session);
  }
  rowCount=0;
  changesSinceAnalyze=0;
}
