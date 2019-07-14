private void onProgressUpdateInternal(String id,DataSource<T> dataSource,float progress,boolean isFinished){
  if (!isExpectedDataSource(id,dataSource)) {
    logMessageAndFailure("ignore_old_datasource @ onProgress",null);
    dataSource.close();
    return;
  }
  if (!isFinished) {
    mSettableDraweeHierarchy.setProgress(progress,false);
  }
}
