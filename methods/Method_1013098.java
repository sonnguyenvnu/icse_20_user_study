public void close() throws IOException {
  log.info("Closing hive data generator...");
  Utils.checkHdfsPath(options.getWorkPath());
}
