/** 
 * {@inheritDoc}
 */
@Override public void handleSilentException(@Nullable Throwable e){
  new ReportBuilder().exception(e).customData(customData).sendSilently().build(reportExecutor);
}
