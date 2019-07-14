private void closeHolesInTable(String table,long did,int minId,int maxId){
  try {
    boolean ok=false;
    SQLiteCursor cursor=database.queryFinalized(String.format(Locale.US,"SELECT start, end FROM " + table + " WHERE uid = %d AND ((end >= %d AND end <= %d) OR (start >= %d AND start <= %d) OR (start >= %d AND end <= %d) OR (start <= %d AND end >= %d))",did,minId,maxId,minId,maxId,minId,maxId,minId,maxId));
    ArrayList<Hole> holes=null;
    while (cursor.next()) {
      if (holes == null) {
        holes=new ArrayList<>();
      }
      int start=cursor.intValue(0);
      int end=cursor.intValue(1);
      if (start == end && start == 1) {
        continue;
      }
      holes.add(new Hole(start,end));
    }
    cursor.dispose();
    if (holes != null) {
      for (int a=0; a < holes.size(); a++) {
        Hole hole=holes.get(a);
        if (maxId >= hole.end - 1 && minId <= hole.start + 1) {
          database.executeFast(String.format(Locale.US,"DELETE FROM " + table + " WHERE uid = %d AND start = %d AND end = %d",did,hole.start,hole.end)).stepThis().dispose();
        }
 else         if (maxId >= hole.end - 1) {
          if (hole.end != minId) {
            try {
              database.executeFast(String.format(Locale.US,"UPDATE " + table + " SET end = %d WHERE uid = %d AND start = %d AND end = %d",minId,did,hole.start,hole.end)).stepThis().dispose();
            }
 catch (            Exception e) {
              FileLog.e(e);
            }
          }
        }
 else         if (minId <= hole.start + 1) {
          if (hole.start != maxId) {
            try {
              database.executeFast(String.format(Locale.US,"UPDATE " + table + " SET start = %d WHERE uid = %d AND start = %d AND end = %d",maxId,did,hole.start,hole.end)).stepThis().dispose();
            }
 catch (            Exception e) {
              FileLog.e(e);
            }
          }
        }
 else {
          database.executeFast(String.format(Locale.US,"DELETE FROM " + table + " WHERE uid = %d AND start = %d AND end = %d",did,hole.start,hole.end)).stepThis().dispose();
          SQLitePreparedStatement state=database.executeFast("REPLACE INTO " + table + " VALUES(?, ?, ?)");
          state.requery();
          state.bindLong(1,did);
          state.bindInteger(2,hole.start);
          state.bindInteger(3,minId);
          state.step();
          state.requery();
          state.bindLong(1,did);
          state.bindInteger(2,maxId);
          state.bindInteger(3,hole.end);
          state.step();
          state.dispose();
        }
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
