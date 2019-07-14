@VisibleForTesting static boolean doesTableExist(DatabaseProvider databaseProvider,String tableName){
  SQLiteDatabase readableDatabase=databaseProvider.getReadableDatabase();
  long count=DatabaseUtils.queryNumEntries(readableDatabase,"sqlite_master","tbl_name = ?",new String[]{tableName});
  return count > 0;
}
