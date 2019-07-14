/** 
 * Save the workspace's data out to file in a safe way: save to a temporary file first and rename it to the real file.
 */
@Override protected void saveWorkspace(){
synchronized (this) {
    File tempFile=new File(_workspaceDir,"workspace.temp.json");
    try {
      if (!saveToFile(tempFile)) {
        tempFile.delete();
        logger.info("Skipping unnecessary workspace save");
        return;
      }
    }
 catch (    Exception e) {
      e.printStackTrace();
      logger.warn("Failed to save workspace");
      return;
    }
    File file=new File(_workspaceDir,"workspace.json");
    File oldFile=new File(_workspaceDir,"workspace.old.json");
    if (oldFile.exists()) {
      oldFile.delete();
    }
    if (file.exists()) {
      file.renameTo(oldFile);
    }
    tempFile.renameTo(file);
    logger.info("Saved workspace");
  }
}
