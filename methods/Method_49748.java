private static boolean isLowMemory(SQLiteException e){
  return e.getMessage().equals(SQLITE_EXCEPTION_DETAIL_MESSAGE);
}
