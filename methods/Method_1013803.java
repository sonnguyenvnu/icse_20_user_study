@Override public void destroy() throws Exception {
  logger.info("[destroy]{}",this);
  File[] files=allFiles();
  if (files != null) {
    for (    File file : files) {
      boolean result=file.delete();
      logger.info("[destroy][delete file]{}, {}",file,result);
    }
  }
}
