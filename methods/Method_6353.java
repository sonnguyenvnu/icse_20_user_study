private void putFeaturedStickersToCache(ArrayList<TLRPC.StickerSetCovered> stickers,final ArrayList<Long> unreadStickers,final int date,final int hash){
  final ArrayList<TLRPC.StickerSetCovered> stickersFinal=stickers != null ? new ArrayList<>(stickers) : null;
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      if (stickersFinal != null) {
        SQLitePreparedStatement state=MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("REPLACE INTO stickers_featured VALUES(?, ?, ?, ?, ?)");
        state.requery();
        int size=4;
        for (int a=0; a < stickersFinal.size(); a++) {
          size+=stickersFinal.get(a).getObjectSize();
        }
        NativeByteBuffer data=new NativeByteBuffer(size);
        NativeByteBuffer data2=new NativeByteBuffer(4 + unreadStickers.size() * 8);
        data.writeInt32(stickersFinal.size());
        for (int a=0; a < stickersFinal.size(); a++) {
          stickersFinal.get(a).serializeToStream(data);
        }
        data2.writeInt32(unreadStickers.size());
        for (int a=0; a < unreadStickers.size(); a++) {
          data2.writeInt64(unreadStickers.get(a));
        }
        state.bindInteger(1,1);
        state.bindByteBuffer(2,data);
        state.bindByteBuffer(3,data2);
        state.bindInteger(4,date);
        state.bindInteger(5,hash);
        state.step();
        data.reuse();
        data2.reuse();
        state.dispose();
      }
 else {
        SQLitePreparedStatement state=MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("UPDATE stickers_featured SET date = ?");
        state.requery();
        state.bindInteger(1,date);
        state.step();
        state.dispose();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
