private List<String> getRemoteExtStopWordDictionarys(){
  List<String> remoteExtStopWordDictFiles=new ArrayList<String>(2);
  String remoteExtStopWordDictCfg=getProperty(REMOTE_EXT_STOP);
  if (remoteExtStopWordDictCfg != null) {
    String[] filePaths=remoteExtStopWordDictCfg.split(";");
    for (    String filePath : filePaths) {
      if (filePath != null && !"".equals(filePath.trim())) {
        remoteExtStopWordDictFiles.add(filePath);
      }
    }
  }
  return remoteExtStopWordDictFiles;
}
