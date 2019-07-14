public void doneHolesInMedia(long did,int max_id,int type) throws Exception {
  if (type == -1) {
    if (max_id == 0) {
      database.executeFast(String.format(Locale.US,"DELETE FROM media_holes_v2 WHERE uid = %d",did)).stepThis().dispose();
    }
 else {
      database.executeFast(String.format(Locale.US,"DELETE FROM media_holes_v2 WHERE uid = %d AND start = 0",did)).stepThis().dispose();
    }
    SQLitePreparedStatement state=database.executeFast("REPLACE INTO media_holes_v2 VALUES(?, ?, ?, ?)");
    for (int a=0; a < DataQuery.MEDIA_TYPES_COUNT; a++) {
      state.requery();
      state.bindLong(1,did);
      state.bindInteger(2,a);
      state.bindInteger(3,1);
      state.bindInteger(4,1);
      state.step();
    }
    state.dispose();
  }
 else {
    if (max_id == 0) {
      database.executeFast(String.format(Locale.US,"DELETE FROM media_holes_v2 WHERE uid = %d AND type = %d",did,type)).stepThis().dispose();
    }
 else {
      database.executeFast(String.format(Locale.US,"DELETE FROM media_holes_v2 WHERE uid = %d AND type = %d AND start = 0",did,type)).stepThis().dispose();
    }
    SQLitePreparedStatement state=database.executeFast("REPLACE INTO media_holes_v2 VALUES(?, ?, ?, ?)");
    state.requery();
    state.bindLong(1,did);
    state.bindInteger(2,type);
    state.bindInteger(3,1);
    state.bindInteger(4,1);
    state.step();
    state.dispose();
  }
}
