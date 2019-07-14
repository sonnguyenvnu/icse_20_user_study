public static <T>ListDataSource<T> create(DataSource<CloseableReference<T>>... dataSources){
  Preconditions.checkNotNull(dataSources);
  Preconditions.checkState(dataSources.length > 0);
  ListDataSource<T> listDataSource=new ListDataSource<T>(dataSources);
  for (  DataSource<CloseableReference<T>> dataSource : dataSources) {
    if (dataSource != null) {
      dataSource.subscribe(listDataSource.new InternalDataSubscriber(),CallerThreadExecutor.getInstance());
    }
  }
  return listDataSource;
}
