static synchronized public File getImportDir(){
  if (importDir == null) {
    File tempDir=servlet.getTempDir();
    importDir=tempDir == null ? new File(".import-temp") : new File(tempDir,"import");
    if (importDir.exists()) {
      try {
        FileUtils.deleteDirectory(importDir);
      }
 catch (      IOException e) {
      }
    }
    importDir.mkdirs();
  }
  return importDir;
}
