@Override public Map<String,String> getAllExportedURLs(){
  Map<String,List<URL>> allExportedUrls=getRepository().getAllExportedUrls();
  if (isEmpty(allExportedUrls)) {
    if (logger.isDebugEnabled()) {
      logger.debug("There is no registered URL.");
    }
    return Collections.emptyMap();
  }
  Map<String,String> result=new HashMap<>();
  allExportedUrls.forEach((serviceKey,urls) -> {
    result.put(serviceKey,jsonUtils.toJSON(urls));
  }
);
  return unmodifiableMap(result);
}
