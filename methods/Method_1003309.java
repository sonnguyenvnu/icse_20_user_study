private static void process(String fileName,boolean quiet){
  if (FileUtils.isDirectory(fileName)) {
    FileUtils.tryDelete(fileName);
  }
 else   if (quiet || fileName.endsWith(Constants.SUFFIX_TEMP_FILE) || fileName.endsWith(Constants.SUFFIX_TRACE_FILE)) {
    FileUtils.tryDelete(fileName);
  }
 else {
    FileUtils.delete(fileName);
  }
}
