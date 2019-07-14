private static ProjectMetadata loadMetaDataIfExist(File projectDir,String fileName){
  ProjectMetadata pm=null;
  File file=new File(projectDir,fileName);
  if (file.exists()) {
    try {
      pm=loadFromFile(file);
    }
 catch (    Exception e) {
      logger.warn("load metadata failed: " + file.getAbsolutePath());
      logger.error(ExceptionUtils.getStackTrace(e));
    }
  }
  return pm;
}
