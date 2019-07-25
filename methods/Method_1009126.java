public static void migrate(Database database,Class<? extends AbstractDao<?,?>>... daoClasses){
  printLog("?Generate temp table?start");
  generateTempTables(database,daoClasses);
  printLog("?Generate temp table?complete");
  ReCreateAllTableListener listener=null;
  if (weakListener != null) {
    listener=weakListener.get();
  }
  if (listener != null) {
    listener.onDropAllTables(database,true);
    printLog("?Drop all table by listener?");
    listener.onCreateAllTables(database,false);
    printLog("?Create all table by listener?");
  }
 else {
    dropAllTables(database,true,daoClasses);
    createAllTables(database,false,daoClasses);
  }
  printLog("?Restore data?start");
  restoreData(database,daoClasses);
  printLog("?Restore data?complete");
}
