private void close() throws IOException {
  log.info("Closing bayes data generator...");
  Utils.checkHdfsPath(options.getWorkPath());
}
