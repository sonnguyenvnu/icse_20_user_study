public void closeHolesInMedia(long did,int minId,int maxId,int type){
  try {
    boolean ok=false;
    SQLiteCursor cursor;
    if (type < 0) {
      cursor=database.queryFinalized(String.format(Locale.US,"SELECT type, start, end FROM media_holes_v2 WHERE uid = %d AND type >= 0 AND ((end >= %d AND end <= %d) OR (start >= %d AND start <= %d) OR (start >= %d AND end <= %d) OR (start <= %d AND end >= %d))",did,minId,maxId,minId,maxId,minId,maxId,minId,maxId));
    }
 else {
      cursor=database.queryFinalized(String.format(Locale.US,"SELECT type, start, end FROM media_holes_v2 WHERE uid = %d AND type = %d AND ((end >= %d AND end <= %d) OR (start >= %d AND start <= %d) OR (start >= %d AND end <= %d) OR (start <= %d AND end >= %d))",did,type,minId,maxId,minId,maxId,minId,maxId,minId,maxId));
    }
    ArrayList<Hole> holes=null;
    while (cursor.next()) {
      if (holes == null) {
        holes=new ArrayList<>();
      }
      int holeType=cursor.intValue(0);
      int start=cursor.intValue(1);
      int end=cursor.intValue(2);
      if (start == end && start == 1) {
        continue;
      }
      holes.add(new Hole(holeType,start,end));
    }
    cursor.dispose();
    if (holes != null) {
      for (int a=0; a < holes.size(); a++) {
        Hole hole=holes.get(a);
        if (maxId >= hole.end - 1 && minId <= hole.start + 1) {
          database.executeFast(String.format(Locale.US,"DELETE FROM media_holes_v2 WHERE uid = %d AND type = %d AND start = %d AND end = %d",did,hole.type,hole.start,hole.end)).stepThis().dispose();
        }
 else         if (maxId >= hole.end - 1) {
          if (hole.end != minId) {
            try {
              database.executeFast(String.format(Locale.US,"UPDATE media_holes_v2 SET end = %d WHERE uid = %d AND type = %d AND start = %d AND end = %d",minId,did,hole.type,hole.start,hole.end)).stepThis().dispose();
            }
 catch (            Exception e) {
              FileLog.e(e);
            }
          }
        }
 else         if (minId <= hole.start + 1) {
          if (hole.start != maxId) {
            try {
              database.executeFast(String.format(Locale.US,"UPDATE media_holes_v2 SET start = %d WHERE uid = %d AND type = %d AND start = %d AND end = %d",maxId,did,hole.type,hole.start,hole.end)).stepThis().dispose();
            }
 catch (            Exception e) {
              FileLog.e(e);
            }
          }
        }
 else {
          database.executeFast(String.format(Locale.US,"DELETE FROM media_holes_v2 WHERE uid = %d AND type = %d AND start = %d AND end = %d",did,hole.type,hole.start,hole.end)).stepThis().dispose();
          SQLitePreparedStatement state=database.executeFast("REPLACE INTO media_holes_v2 VALUES(?, ?, ?, ?)");
          state.requery();
          state.bindLong(1,did);
          state.bindInteger(2,hole.type);
          state.bindInteger(3,hole.start);
          state.bindInteger(4,minId);
          state.step();
          state.requery();
          state.bindLong(1,did);
          state.bindInteger(2,hole.type);
          state.bindInteger(3,maxId);
          state.bindInteger(4,hole.end);
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
