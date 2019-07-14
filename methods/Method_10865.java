public static void migrate(StandardDatabase db,Class<? extends AbstractDao<?,?>>... daoClasses){
  generateNewTablesIfNotExists(db,daoClasses);
  printLog("?Generate temp table?complete");
  generateTempTables(db,daoClasses);
  dropAllTables(db,true,daoClasses);
  createAllTables(db,false,daoClasses);
  printLog("?Restore data?start");
  restoreData(db,daoClasses);
  printLog("?Restore data?complete");
}
