public void add(File f,int cleanTime){
  files.put(f,cleanTime);
  try {
    dumpFile();
  }
 catch (  IOException e) {
    LOGGER.error("An error occurred while trying to add \"{}\" to temporary file management: {}",f.getAbsolutePath(),e.getMessage());
    LOGGER.trace("",e);
  }
}
