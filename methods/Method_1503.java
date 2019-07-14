private void onDataSourceFailed(DataSource<CloseableReference<T>> dataSource){
  setFailure(dataSource.getFailureCause());
}
