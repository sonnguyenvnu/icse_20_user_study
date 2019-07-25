private void init() throws SQLException {
  dataSource=new ForceSwitchableDataSource(new XPipeDataSourceConfigureProvider(descriptor));
  ForceSwitchableDataSourceHolder.getInstance().setDataSource(dataSource);
  dataSource.addListener(new IForceSwitchableDataSource.SwitchListener(){
    @Override public void onForceSwitchSuccess(    SwitchableDataSourceStatus currentStatus){
      logger.info("[onForceSwitchSuccess] current status, {}",currentStatus);
      EventMonitor.DEFAULT.logEvent("DAL.SWITCH","SUCCESS");
    }
    @Override public void onForceSwitchFail(    SwitchableDataSourceStatus currentStatus,    Throwable cause){
      logger.error("[onForceSwitchFail] current status, {}",currentStatus,cause);
      EventMonitor.DEFAULT.logEvent("DAL.SWITCH","FAILURE");
    }
    @Override public void onRestoreSuccess(    SwitchableDataSourceStatus currentStatus){
      logger.info("[onRestoreSuccess] current status, {}",currentStatus);
      EventMonitor.DEFAULT.logEvent("DAL.RESTORE","SUCCESS");
    }
    @Override public void onRestoreFail(    SwitchableDataSourceStatus currentStatus,    Throwable cause){
      logger.error("[onRestoreFail] current status, {}",currentStatus,cause);
      EventMonitor.DEFAULT.logEvent("DAL.RESTORE","FAILURE");
    }
  }
);
}
