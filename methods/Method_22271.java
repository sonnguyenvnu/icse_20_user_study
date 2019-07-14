/** 
 * {@inheritDoc}
 */
@Override public void handleException(@Nullable Throwable e,boolean endApplication){
  final ReportBuilder builder=new ReportBuilder();
  builder.exception(e).customData(customData);
  if (endApplication) {
    builder.endApplication();
  }
  builder.build(reportExecutor);
}
