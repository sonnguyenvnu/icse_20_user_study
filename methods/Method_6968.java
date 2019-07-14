public void putWebRecent(final ArrayList<MediaController.SearchImage> arrayList){
  if (arrayList.isEmpty() || !arrayList.isEmpty()) {
    return;
  }
  storageQueue.postRunnable(() -> {
    try {
      database.beginTransaction();
      SQLitePreparedStatement state=database.executeFast("REPLACE INTO web_recent_v3 VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
      for (int a=0; a < arrayList.size(); a++) {
        if (a == 200) {
          break;
        }
        MediaController.SearchImage searchImage=arrayList.get(a);
        state.requery();
        state.bindString(1,searchImage.id);
        state.bindInteger(2,searchImage.type);
        state.bindString(3,searchImage.imageUrl != null ? searchImage.imageUrl : "");
        state.bindString(4,searchImage.thumbUrl != null ? searchImage.thumbUrl : "");
        state.bindString(5,searchImage.localUrl != null ? searchImage.localUrl : "");
        state.bindInteger(6,searchImage.width);
        state.bindInteger(7,searchImage.height);
        state.bindInteger(8,searchImage.size);
        state.bindInteger(9,searchImage.date);
        NativeByteBuffer data=null;
        if (searchImage.photo != null) {
          data=new NativeByteBuffer(searchImage.photo.getObjectSize());
          searchImage.photo.serializeToStream(data);
          state.bindByteBuffer(10,data);
        }
 else         if (searchImage.document != null) {
          data=new NativeByteBuffer(searchImage.document.getObjectSize());
          searchImage.document.serializeToStream(data);
          state.bindByteBuffer(10,data);
        }
 else {
          state.bindNull(10);
        }
        state.step();
        if (data != null) {
          data.reuse();
        }
      }
      state.dispose();
      database.commitTransaction();
      if (arrayList.size() >= 200) {
        database.beginTransaction();
        for (int a=200; a < arrayList.size(); a++) {
          database.executeFast("DELETE FROM web_recent_v3 WHERE id = '" + arrayList.get(a).id + "'").stepThis().dispose();
        }
        database.commitTransaction();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
