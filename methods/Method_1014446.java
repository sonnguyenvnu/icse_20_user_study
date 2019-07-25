public void activate(){
  logger.debug("MapDB persistence service is being activated");
  threadPool=ThreadPoolManager.getPool(getClass().getSimpleName());
  File folder=new File(DB_FOLDER_NAME);
  if (!folder.exists()) {
    if (!folder.mkdirs()) {
      logger.warn("Failed to create one or more directories in the path '{}'",DB_FOLDER_NAME);
      logger.warn("MapDB persistence service activation has failed.");
      return;
    }
  }
  File dbFile=new File(DB_FOLDER_NAME,DB_FILE_NAME);
  db=DBMaker.newFileDB(dbFile).closeOnJvmShutdown().make();
  map=db.createTreeMap("itemStore").makeOrGet();
  logger.debug("MapDB persistence service is now activated");
}
