protected void recover(){
  boolean recovered=false;
  for (  File file : _workspaceDir.listFiles()) {
    if (file.isDirectory() && !file.isHidden()) {
      String dirName=file.getName();
      if (file.getName().endsWith(PROJECT_DIR_SUFFIX)) {
        String idString=dirName.substring(0,dirName.length() - PROJECT_DIR_SUFFIX.length());
        long id=-1;
        try {
          id=Long.parseLong(idString);
        }
 catch (        NumberFormatException e) {
        }
        if (id > 0 && !_projectsMetadata.containsKey(id)) {
          if (loadProjectMetadata(id)) {
            logger.info("Recovered project named " + getProjectMetadata(id).getName() + " in directory " + dirName);
            recovered=true;
          }
 else {
            logger.warn("Failed to recover project in directory " + dirName);
            file.renameTo(new File(file.getParentFile(),dirName + ".corrupted"));
          }
        }
      }
    }
  }
  if (recovered) {
    saveWorkspace();
  }
}
