@Override public void capture(String directory) throws IOException {
  _log.debug("Switching to capture mode. Directory: " + directory);
  _filter.setFilter(PASS_THROUGH_FILTER);
  try {
    _filter.setFilter(new CaptureFilter(new DirectoryDbSink(directory,new DefaultMessageSerializer())));
  }
 catch (  IOException e) {
    _log.warn("Error switching to capture mode",e);
    throw e;
  }
catch (  RuntimeException e) {
    _log.warn("Error switching to capture mode",e);
    throw e;
  }
}
