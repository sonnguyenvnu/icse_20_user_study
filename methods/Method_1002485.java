@Override public void replay(String directory) throws IOException {
  _log.debug("Switching to replay mode. Directory: " + directory);
  _filter.setFilter(PASS_THROUGH_FILTER);
  try {
    _filter.setFilter(new ReplayFilter(new DirectoryDbSource(directory,new DefaultMessageSerializer())));
  }
 catch (  IOException e) {
    _log.warn("Error switching to replay mode",e);
    throw e;
  }
catch (  RuntimeException e) {
    _log.warn("Error switching to capture mode",e);
    throw e;
  }
}
