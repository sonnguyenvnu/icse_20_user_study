@Override public void destroy() throws Exception {
  logger.info("[destroy]{}",this);
  FileUtils.recursiveDelete(baseDir);
}
