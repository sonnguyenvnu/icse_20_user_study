public static void createFirstHoles(long did,SQLitePreparedStatement state5,SQLitePreparedStatement state6,int messageId) throws Exception {
  state5.requery();
  state5.bindLong(1,did);
  state5.bindInteger(2,messageId == 1 ? 1 : 0);
  state5.bindInteger(3,messageId);
  state5.step();
  for (int b=0; b < DataQuery.MEDIA_TYPES_COUNT; b++) {
    state6.requery();
    state6.bindLong(1,did);
    state6.bindInteger(2,b);
    state6.bindInteger(3,messageId == 1 ? 1 : 0);
    state6.bindInteger(4,messageId);
    state6.step();
  }
}
