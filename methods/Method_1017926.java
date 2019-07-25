@Override public InspectionStatus inspect(Configuration configuration){
  InspectionStatus status=new InspectionStatus(false);
  try {
    ((com.thinkbiganalytics.hive.service.RefreshableDataSource)ds).testConnection();
  }
 catch (  SQLException e) {
    String msg=String.format("Failed to connect to Hive: %s",e.getMessage());
    LOG.error(msg,e);
    status.addError(msg);
    return status;
  }
  status.setValid(true);
  return status;
}
