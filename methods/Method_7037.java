private void doneHolesInTable(String table,long did,int max_id) throws Exception {
  if (max_id == 0) {
    database.executeFast(String.format(Locale.US,"DELETE FROM " + table + " WHERE uid = %d",did)).stepThis().dispose();
  }
 else {
    database.executeFast(String.format(Locale.US,"DELETE FROM " + table + " WHERE uid = %d AND start = 0",did)).stepThis().dispose();
  }
  SQLitePreparedStatement state=database.executeFast("REPLACE INTO " + table + " VALUES(?, ?, ?)");
  state.requery();
  state.bindLong(1,did);
  state.bindInteger(2,1);
  state.bindInteger(3,1);
  state.step();
  state.dispose();
}
