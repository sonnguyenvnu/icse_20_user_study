public static void _XXXXX_(File dir) throws IOException {
  if (!dir.exists()) {
    File parent=dir.getParentFile();
    File preV3versionFile=new File(dir.getParent(),BookKeeperConstants.VERSION_FILENAME);
    final AtomicBoolean oldDataExists=new AtomicBoolean(false);
    parent.list(new FilenameFilter(){
      @Override public boolean accept(      File dir,      String name){
        if (name.endsWith(".txn") || name.endsWith(".idx") || name.endsWith(".log")) {
          oldDataExists.set(true);
        }
        return true;
      }
    }
);
    if (preV3versionFile.exists() || oldDataExists.get()) {
      String err="Directory layout version is less than 3, upgrade needed";
      LOG.error(err);
      throw new IOException(err);
    }
    if (!dir.mkdirs()) {
      String err="Unable to create directory " + dir;
      LOG.error(err);
      throw new IOException(err);
    }
  }
}