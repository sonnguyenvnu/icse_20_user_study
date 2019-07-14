protected boolean loadFromFile(File file){
  logger.info("Loading workspace: {}",file.getAbsolutePath());
  _projectsMetadata.clear();
  boolean found=false;
  if (file.exists() || file.canRead()) {
    try {
      ParsingUtilities.mapper.readerForUpdating(this).readValue(file);
      found=true;
    }
 catch (    IOException e) {
      logger.warn(e.toString());
    }
  }
  return found;
}
