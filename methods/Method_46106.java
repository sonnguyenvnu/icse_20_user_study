static Map<String,ProviderGroup> loadBackupFileToCache(String address){
  Map<String,ProviderGroup> memoryCache=new ConcurrentHashMap<String,ProviderGroup>();
  File regFile=new File(address);
  if (!regFile.exists()) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Load backup file failure cause by can't found file: {}",regFile.getAbsolutePath());
    }
  }
 else {
    try {
      String content=FileUtils.file2String(regFile);
      Map<String,ProviderGroup> tmp=unMarshal(content);
      if (LOGGER.isInfoEnabled()) {
        LOGGER.info("Load backup file from {}",regFile.getAbsolutePath());
      }
      if (tmp != null) {
        memoryCache.putAll(tmp);
      }
    }
 catch (    IOException e) {
      throw new SofaRpcRuntimeException("Error when read backup file: " + regFile.getAbsolutePath(),e);
    }
  }
  return memoryCache;
}
