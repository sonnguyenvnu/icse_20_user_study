protected <T>T loadMetaData(InputStream is,Class<T> type){
  ObjectMapper mapper=new ObjectMapper();
  try {
    T result=mapper.readValue(is,type);
    logger.debug(result.toString());
    return result;
  }
 catch (  Exception e) {
    logger.warn("An exception occured while loading the metadata file from the file system. This is just a warning and can be ignored, but it may lead to unexpected results, so it's better to address it.",e);
    return null;
  }
}
