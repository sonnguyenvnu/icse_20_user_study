@Override public int update(){
  session.commit(true);
  session.getUser().checkAdmin();
  Database db=session.getDatabase();
  if (table != null) {
    analyzeTable(session,table,sampleRows,true);
  }
 else {
    for (    Table table : db.getAllTablesAndViews(false)) {
      analyzeTable(session,table,sampleRows,true);
    }
  }
  return 0;
}
