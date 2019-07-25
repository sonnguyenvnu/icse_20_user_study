private void scan(){
  long now=System.currentTimeMillis();
  for (Iterator<Entry<File,Integer>> it=files.entrySet().iterator(); it.hasNext(); ) {
    Entry<File,Integer> entry=it.next();
    if (!entry.getKey().exists()) {
      it.remove();
      continue;
    }
    if ((now - entry.getKey().lastModified()) > entry.getValue()) {
      it.remove();
      if (!entry.getKey().delete()) {
        LOGGER.warn("Failed to delete temporary file \"{}\"",entry.getKey().getAbsolutePath());
      }
    }
  }
  try {
    dumpFile();
  }
 catch (  IOException e) {
    LOGGER.error("An error occurred while trying to write the temporary file management file: {}",e.getMessage());
    LOGGER.trace("",e);
  }
}
