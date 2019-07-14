public void putWallpapers(final ArrayList<TLRPC.WallPaper> wallPapers,int action){
  storageQueue.postRunnable(() -> {
    try {
      if (action == 1) {
        database.executeFast("DELETE FROM wallpapers2 WHERE 1").stepThis().dispose();
      }
      database.beginTransaction();
      SQLitePreparedStatement state;
      if (action != 0) {
        state=database.executeFast("REPLACE INTO wallpapers2 VALUES(?, ?, ?)");
      }
 else {
        state=database.executeFast("UPDATE wallpapers2 SET data = ? WHERE uid = ?");
      }
      for (int a=0, N=wallPapers.size(); a < N; a++) {
        TLRPC.TL_wallPaper wallPaper=(TLRPC.TL_wallPaper)wallPapers.get(a);
        state.requery();
        NativeByteBuffer data=new NativeByteBuffer(wallPaper.getObjectSize());
        wallPaper.serializeToStream(data);
        if (action != 0) {
          state.bindLong(1,wallPaper.id);
          state.bindByteBuffer(2,data);
          state.bindInteger(3,action == 2 ? -1 : a);
        }
 else {
          state.bindByteBuffer(1,data);
          state.bindLong(2,wallPaper.id);
        }
        state.step();
        data.reuse();
      }
      state.dispose();
      database.commitTransaction();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
