private List<String> getExtStopWordDictionarys(){
  List<String> extStopWordDictFiles=new ArrayList<String>(2);
  String extStopWordDictCfg=getProperty(EXT_STOP);
  if (extStopWordDictCfg != null) {
    String[] filePaths=extStopWordDictCfg.split(";");
    for (    String filePath : filePaths) {
      if (filePath != null && !"".equals(filePath.trim())) {
        Path file=PathUtils.get(getDictRoot(),filePath.trim());
        walkFileTree(extStopWordDictFiles,file);
      }
    }
  }
  return extStopWordDictFiles;
}
