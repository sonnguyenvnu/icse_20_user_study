@Override public void initialize(DataSourceDescriptor descriptor){
  this.descriptor=descriptor;
  try {
    init();
  }
 catch (  SQLException e) {
    logger.error("[initialize]",e);
  }
}
