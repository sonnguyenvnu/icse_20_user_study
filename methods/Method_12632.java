private List<String> getRemoteExtDictionarys(){
  List<String> remoteExtDictFiles=new ArrayList<String>(2);
  String remoteExtDictCfg=getProperty(REMOTE_EXT_DICT);
  if (remoteExtDictCfg != null) {
    String[] filePaths=remoteExtDictCfg.split(";");
    for (    String filePath : filePaths) {
      if (filePath != null && !"".equals(filePath.trim())) {
        remoteExtDictFiles.add(filePath);
      }
    }
  }
  return remoteExtDictFiles;
}
