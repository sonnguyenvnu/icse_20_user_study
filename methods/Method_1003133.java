/** 
 * Initialize the MVStore.
 * @param db the database
 * @return the store
 */
public static Store init(final Database db){
  Store store=db.getStore();
  if (store != null) {
    return store;
  }
  byte[] key=db.getFileEncryptionKey();
  String dbPath=db.getDatabasePath();
  MVStore.Builder builder=new MVStore.Builder();
  store=new Store();
  boolean encrypted=false;
  if (dbPath != null) {
    String fileName=dbPath + Constants.SUFFIX_MV_FILE;
    MVStoreTool.compactCleanUp(fileName);
    builder.fileName(fileName);
    builder.pageSplitSize(db.getPageSize());
    if (db.isReadOnly()) {
      builder.readOnly();
    }
 else {
      boolean exists=FileUtils.exists(fileName);
      if (exists && !FileUtils.canWrite(fileName)) {
      }
 else {
        String dir=FileUtils.getParent(fileName);
        FileUtils.createDirectories(dir);
      }
      int autoCompactFillRate=db.getSettings().maxCompactCount;
      if (autoCompactFillRate <= 100) {
        builder.autoCompactFillRate(autoCompactFillRate);
      }
    }
    if (key != null) {
      encrypted=true;
      builder.encryptionKey(decodePassword(key));
    }
    if (db.getSettings().compressData) {
      builder.compress();
      builder.pageSplitSize(64 * 1024);
    }
    builder.backgroundExceptionHandler(new UncaughtExceptionHandler(){
      @Override public void uncaughtException(      Thread t,      Throwable e){
        db.setBackgroundException(DbException.convert(e));
      }
    }
);
  }
  store.open(db,builder,encrypted);
  db.setStore(store);
  return store;
}
