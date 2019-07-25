private void close() throws IOException {
  log.info("Closing nutch data generator...");
  Utils.checkHdfsPath(options.getWorkPath());
}
