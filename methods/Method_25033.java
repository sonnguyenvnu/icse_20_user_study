private String findIndexFileInDirectory(File directory){
  for (  String fileName : SimpleWebServer.INDEX_FILE_NAMES) {
    File indexFile=new File(directory,fileName);
    if (indexFile.isFile()) {
      return fileName;
    }
  }
  return null;
}
