private List<String> getExtDictionarys(){
  List<String> extDictFiles=new ArrayList<String>(2);
  String extDictCfg=getProperty(EXT_DICT);
  if (extDictCfg != null) {
    String[] filePaths=extDictCfg.split(";");
    for (    String filePath : filePaths) {
      if (filePath != null && !"".equals(filePath.trim())) {
        Path file=PathUtils.get(getDictRoot(),filePath.trim());
        walkFileTree(extDictFiles,file);
      }
    }
  }
  return extDictFiles;
}
