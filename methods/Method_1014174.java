@Activate public void activate(){
  dbFolderName=ConfigConstants.getUserDataFolder() + File.separator + dbFolderName;
  File folder=new File(dbFolderName);
  if (!folder.exists()) {
    folder.mkdirs();
  }
  File dbFile=new File(dbFolderName,DB_FILE_NAME);
  db=DBMaker.newFileDB(dbFile).closeOnJvmShutdown().make();
  logger.debug("Opened MapDB file at '{}'.",dbFile.getAbsolutePath());
}
