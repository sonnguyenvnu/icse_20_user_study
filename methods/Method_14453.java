synchronized public static void save(Project project) throws IOException {
synchronized (project) {
    long id=project.id;
    File dir=((FileProjectManager)ProjectManager.singleton).getProjectDir(id);
    File tempFile=new File(dir,"data.temp.zip");
    try {
      saveToFile(project,tempFile);
    }
 catch (    IOException e) {
      e.printStackTrace();
      logger.warn("Failed to save project {}",id);
      try {
        tempFile.delete();
      }
 catch (      Exception e2) {
      }
      throw e;
    }
    File file=new File(dir,"data.zip");
    File oldFile=new File(dir,"data.old.zip");
    if (file.exists()) {
      file.renameTo(oldFile);
    }
    tempFile.renameTo(file);
    if (oldFile.exists()) {
      oldFile.delete();
    }
    project.setLastSave();
    logger.info("Saved project '{}'",id);
  }
}
