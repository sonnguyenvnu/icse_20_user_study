public static <T>DataSource<T> immediateFailedDataSource(Throwable failure){
  SimpleDataSource<T> simpleDataSource=SimpleDataSource.create();
  simpleDataSource.setFailure(failure);
  return simpleDataSource;
}
